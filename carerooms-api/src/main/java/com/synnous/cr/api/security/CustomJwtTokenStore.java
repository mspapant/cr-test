package com.synnous.cr.api.security;

import com.synnous.cr.core.domain.entity.UserRefreshToken;
import com.synnous.cr.core.domain.repository.UserRefreshTokenRepository;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Date;
import java.util.Map;

/**
 * The custom jwt token store
 *
 * @author : Manos Papantonakos on 17/1/2017.
 */
public class CustomJwtTokenStore extends JwtTokenStore {

    private final CustomJwtAccessTokenConverter jwtTokenEnhancer;
    private final UserRefreshTokenRepository userRefreshTokenRepository;

    public CustomJwtTokenStore(CustomJwtAccessTokenConverter jwtTokenEnhancer, UserRefreshTokenRepository userRefreshTokenRepository) {
        super(jwtTokenEnhancer);
        this.jwtTokenEnhancer = jwtTokenEnhancer;
        this.userRefreshTokenRepository = userRefreshTokenRepository;
    }

    /* (non-Javadoc)
     * @see org.springframework.security.oauth2.provider.token.store.JwtTokenStore#storeRefreshToken(org.springframework.security.oauth2.common.OAuth2RefreshToken, org.springframework.security.oauth2.provider.OAuth2Authentication)
     */
    @Override
    public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
        UserRefreshToken userRefreshToken = getUserRefreshToken(refreshToken.getValue());
        if (userRefreshToken.getUserId() != null) {
            userRefreshTokenRepository.save(userRefreshToken);
        }
        super.storeRefreshToken(refreshToken, authentication);
    }

    /* (non-Javadoc)
     * @see org.springframework.security.oauth2.provider.token.store.JwtTokenStore#readRefreshToken(java.lang.String)
     */
    @Override
    public OAuth2RefreshToken readRefreshToken(String tokenValue) {
        OAuth2RefreshToken token = super.readRefreshToken(tokenValue);
        if (token == null) {
            return null;
        }
        UserRefreshToken userRefreshToken = getUserRefreshToken(token.getValue());
        if (userRefreshToken.getUserId() == null) {
            return super.readRefreshToken(tokenValue);
        }
        userRefreshToken = userRefreshTokenRepository.findByTokenAndUserId(userRefreshToken.getToken(), userRefreshToken.getUserId());
        if (userRefreshToken == null || userRefreshToken.getExpirationDate().before(new Date())) {
            return null;
        }
        return token;
    }

    /* (non-Javadoc)
     * @see org.springframework.security.oauth2.provider.token.store.JwtTokenStore#removeRefreshToken(org.springframework.security.oauth2.common.OAuth2RefreshToken)
     */
    @Override
    public void removeRefreshToken(OAuth2RefreshToken token) {
        UserRefreshToken userRefreshToken = getUserRefreshToken(token.getValue());
        if (userRefreshToken.getUserId() == null) {
            super.removeRefreshToken(token);
            return;
        }
        userRefreshToken = userRefreshTokenRepository.findByTokenAndUserId(userRefreshToken.getToken(), userRefreshToken.getUserId());
        if (userRefreshToken != null) {
            userRefreshTokenRepository.delete(userRefreshToken);
        }
        super.removeRefreshToken(token);
    }

    private UserRefreshToken getUserRefreshToken(String tokenValue) {
        Map<String, Object> tokenContents = this.jwtTokenEnhancer.decode(tokenValue);
        return new UserRefreshToken((String) tokenContents.get("jti"), (String) tokenContents.get("user_name"), new Date(((Long) tokenContents.get("exp")).longValue() * 1000L));
    }
}
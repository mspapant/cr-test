package com.synnous.cr.api.security;

import com.synnous.cr.core.domain.entity.User;
import com.synnous.cr.core.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.Map;

/**
 * Custom authentication provider.
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 12/9/15
 * @since JDK1.8
 */
public class UserAuthenticationProvider implements AuthenticationProvider {

    /** The user service. */
    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        String username = token.getName();
        String password = (String) token.getCredentials();
        Map<String, String> details = (Map) token.getDetails();
        User user;
        if (authorizationWithUserToken(token)) {
            user = userService.authenticateByUserToken(password);
        } else {
            user = userService.authenticateUser(username, password);
        }
        return new UsernamePasswordAuthenticationToken(user, token.getCredentials(), user.getAuthorities());
    }

    private boolean authorizationWithUserToken(final UsernamePasswordAuthenticationToken token) {
        if (token.getDetails() instanceof Map) {
            Map<String, String> details = (Map<String, String>) token.getDetails();
            return details.get("type") != null && details.get("type").equals("USER_TOKEN");
        }
        return false;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}

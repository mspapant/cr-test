package com.synnous.cr.api.security;

import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Map;

public class CustomJwtAccessTokenConverter extends JwtAccessTokenConverter {

	/* (non-Javadoc)
	 * @see org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter#decode(java.lang.String)
	 */
	@Override
	protected Map<String, Object> decode(String token) {
		return super.decode(token);
	}
}

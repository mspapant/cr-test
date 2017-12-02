package com.synnous.cr.api.config;

import com.synnous.cr.api.controller.ex.RestResponseEntityExceptionHandler;
import com.synnous.cr.api.security.CustomAuthenticationEntryPoint;
import com.synnous.cr.api.security.CustomJwtAccessTokenConverter;
import com.synnous.cr.api.security.CustomJwtTokenStore;
import com.synnous.cr.core.domain.repository.UserRefreshTokenRepository;
import com.synnous.cr.core.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Oauth2 configuration
 */
@Configuration
@EnableWebMvc
@EnableWebSecurity
public class OAuth2Configuration {

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Autowired
        @SuppressWarnings("SpringJavaAutowiringInspection")
        private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

        @Autowired
        private UserRepository userRepository;

        @Override
        public void configure(final ResourceServerSecurityConfigurer resources) throws Exception {
            super.configure(resources);
            resources.authenticationEntryPoint(customAuthenticationEntryPoint);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {

            http
                    .exceptionHandling()
                    .authenticationEntryPoint(customAuthenticationEntryPoint)
                    .and()
                    .authorizeRequests()
                    .antMatchers("/auth/**", "/api/public/**", "/doc/**").permitAll()
                    .antMatchers("/api/**").authenticated()
                    .and().addFilterBefore(new RequestContextFilter(), BasicAuthenticationFilter.class);
        }

    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter implements EnvironmentAware {

        private static final String ENV_OAUTH = "authentication.oauth.";
        private static final String WEB = "web.";
        private static final String TEST = "test.";
        private static final String PROP_CLIENT_ID = "clientId";
        private static final String PROP_SECRET = "secret";
        private static final String PROP_SCOPE = "scope";
        private static final String PROP_GRANT_TYPES = "grantTypes";
        private static final String PROP_ACCESS_TOKEN_VALIDITY_SECONDS = "accessTokenValidityInSeconds";
        private static final String PROP_REFRESH_TOKEN_VALIDITY_SECONDS = "refreshTokenValidityInSeconds";

        private RelaxedPropertyResolver propertyResolver;

        @Autowired
        @SuppressWarnings("SpringJavaAutowiringInspection")
        private UserDetailsService userService;

        @Autowired
        @SuppressWarnings("SpringJavaAutowiringInspection")
        private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;


        @Bean
        public CustomJwtAccessTokenConverter accessTokenConverter() {
            CustomJwtAccessTokenConverter converter = new CustomJwtAccessTokenConverter();
            converter.setSigningKey("r:k.fECeK:2C3Sp9Y*Z#2,;567ME!XE!vDeq)vSy*+4}>(`Gd~g$^(FX)AL");
            return converter;
        }

        @Autowired
        @SuppressWarnings("SpringJavaAutowiringInspection")
        private UserRefreshTokenRepository userRefreshTokenRepository;

        @Bean
        public TokenStore tokenStore() {
            return new CustomJwtTokenStore(accessTokenConverter(), userRefreshTokenRepository);
        }


        @Autowired
        @SuppressWarnings("SpringJavaAutowiringInspection")
        private AuthenticationManager authenticationManager;

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints
                    .exceptionTranslator(restResponseEntityExceptionHandler())
                    .tokenStore(tokenStore())
                    .userDetailsService(userService)
                    .accessTokenConverter(accessTokenConverter())
                    .reuseRefreshTokens(false)
                    .authenticationManager(authenticationManager);
        }

        @Bean
        public RestResponseEntityExceptionHandler restResponseEntityExceptionHandler() {
            return new RestResponseEntityExceptionHandler();
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients
                    .inMemory()
                    .withClient(propertyResolver.getProperty(WEB + PROP_CLIENT_ID))
                    .scopes(propertyResolver.getProperty(WEB + PROP_SCOPE).split(","))
                    .authorizedGrantTypes(propertyResolver.getProperty(WEB + PROP_GRANT_TYPES).split(","))
                    .secret(propertyResolver.getProperty(WEB + PROP_SECRET))
                    .accessTokenValiditySeconds(propertyResolver.getProperty(WEB + PROP_ACCESS_TOKEN_VALIDITY_SECONDS, Integer.class))
                    .and()
                    .withClient(propertyResolver.getProperty(TEST + PROP_CLIENT_ID))
                    .scopes(propertyResolver.getProperty(TEST + PROP_SCOPE).split(","))
                    .authorizedGrantTypes(propertyResolver.getProperty(TEST + PROP_GRANT_TYPES).split(","))
                    .secret(propertyResolver.getProperty(TEST + PROP_SECRET))
                    .accessTokenValiditySeconds(propertyResolver.getProperty(TEST + PROP_ACCESS_TOKEN_VALIDITY_SECONDS, Integer.class))
            ;
        }

        @Override
        public void setEnvironment(Environment environment) {
            this.propertyResolver = new RelaxedPropertyResolver(environment, ENV_OAUTH);
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            oauthServer.authenticationEntryPoint(customAuthenticationEntryPoint);
        }
    }

}

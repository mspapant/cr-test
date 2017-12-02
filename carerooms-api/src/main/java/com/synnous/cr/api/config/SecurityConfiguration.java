package com.synnous.cr.api.config;

import com.synnous.cr.api.filter.AccessControlHttpFilter;
import com.synnous.cr.api.security.UserAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.HeaderWriter;
import org.springframework.security.web.header.writers.CacheControlHeadersWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Security configuration class.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring()
                .antMatchers("/messaging/**")
                .antMatchers("/public/**")
                .antMatchers("/doc/**");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Prevent the HTTP response header of "Pragma: no-cache".
        http.headers().addHeaderWriter(new HeaderWriter() {

            CacheControlHeadersWriter originalWriter = new CacheControlHeadersWriter();

            @Override
            public void writeHeaders(HttpServletRequest request, HttpServletResponse response) {
                Collection<String> headerNames = response.getHeaderNames();
                String requestUri = request.getRequestURI();
                if (!request.getMethod().equals("GET")) {
                    originalWriter.writeHeaders(request, response);
                } else {
                    //write header here or do nothing if it was set in the code
                }
            }
        });

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        List<AuthenticationProvider> providers = new ArrayList<AuthenticationProvider>();
        providers.add(authenticationProvider());
        return new ProviderManager(providers);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new UserAuthenticationProvider();
    }

    @Bean
    public AccessControlHttpFilter accessControlHttpFilter() {
        return new AccessControlHttpFilter();
    }
}


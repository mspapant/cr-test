package com.synnous.cr.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * The web configuration
 *
 * @author : Manos Papantonakos.
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/doc/v2/api-docs", "/v2/api-docs");
        registry.addRedirectViewController("/doc/configuration/ui", "/configuration/ui");
        registry.addRedirectViewController("/doc/configuration/ui", "/configuration/ui");
        registry.addRedirectViewController("/doc/configuration/security", "/configuration/security");
        registry.addRedirectViewController("/doc/swagger-resources", "/swagger-resources");
        registry.addRedirectViewController("/doc", "/doc/swagger-ui.html");
        registry.addRedirectViewController("/doc/", "/doc/swagger-ui.html");
        registry.addRedirectViewController("/doc/swagger-resources/configuration/ui", "/configuration/ui");
        registry.addRedirectViewController("**/configuration/ui", "/swagger-resources/configuration/ui");
        registry.addRedirectViewController("**/configuration/security", "/swagger-resources/configuration/security");
        registry.addRedirectViewController("**/swagger-oauth.js", "/oauth.js");
        registry.addRedirectViewController("/", "index.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc/**").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/**/oauth.js").addResourceLocations("classpath:/oauth.js");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/app/");
    }

    @Bean
    public AuthenticationPrincipalArgumentResolver authenticationPrincipalArgumentResolver() {
        return new AuthenticationPrincipalArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(authenticationPrincipalArgumentResolver());
    }
}
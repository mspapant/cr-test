package com.synnous.cr.api.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationCodeGrant;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.TokenEndpoint;
import springfox.documentation.service.TokenRequestEndpoint;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;

import java.util.Arrays;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Swagger configuration for documentation.
 *
 * @author : Manos Papantonakos
 */
@Configuration
@EnableSwagger2
@Import({Swagger2DocumentationConfiguration.class})
public class SwaggerConfig {

    public static final String securitySchemaOAuth2 = "oauth2";
    public static final String authorizationScopeGlobal = "global";
    public static final String authorizationScopeGlobalDesc = "accessEverything";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(internalPaths())
                .build()
                .enableUrlTemplating(false)
                .ignoredParameterTypes(AuthenticationPrincipal.class)
                .securitySchemes(Arrays.asList(authorizationCodeFlow()))
                .securityContexts(Arrays.asList(securityContext()));

    }

    private Predicate<String> internalPaths() {
        return PathSelectors.regex("/api.*");
    }


    private AuthorizationScope getAuthorizationScope() {
        return new AuthorizationScope(authorizationScopeGlobal, authorizationScopeGlobal);
    }

    @Bean(name = "swaggerConf")
    public SecurityConfiguration securityConfiguration() {
        return new SecurityConfiguration("test", "SjDyqMQVFxxCvjKHzAwBT", "application", "carerooms", null, ApiKeyVehicle.HEADER, "application", ",");
    }


    /**
     * One of the 5 or so OAuth2 authorisation flows.
     * <p/>
     * The Authorization Code or Web server flow is suitable for clients that can interact with the end-user’s user-agent (typically a Web browser),
     * and that can receive incoming requests from the authorization server (can act as an HTTP server).
     *
     * @return
     */
    private OAuth authorizationCodeFlow() {

        // Other grant types are: "authorization_code",
        // "refresh_token",
        // "password"
        return new OAuth(
                securitySchemaOAuth2,
                newArrayList(getAuthorizationScope()),
                newArrayList(
                        new AuthorizationCodeGrant(
                                new TokenRequestEndpoint("/carerooms/oauth/token", "foo", "bar"),
                                new TokenEndpoint("/carerooms/oauth/token", "access_code"))
                ));
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(internalPaths())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope(authorizationScopeGlobal, authorizationScopeGlobalDesc);
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return newArrayList(
                new SecurityReference(securitySchemaOAuth2, authorizationScopes));
    }

}

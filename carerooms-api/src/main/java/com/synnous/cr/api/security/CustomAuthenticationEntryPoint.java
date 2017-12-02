package com.synnous.cr.api.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Returns a 401 error code (Unauthorized) to the client.
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /** The logger. */
    private final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);

    /** The access control origin header. */
    private static final String ACCESS_CONTROL_ALLOW_ORIGIN_HEADER = "Access-Control-Allow-Origin";

    /** The access control origin header value. */
    private static final String ACCESS_CONTROL_ALLOW_ORIGIN_HEADER_VALUE = "*";

    /** The access control allow methods header. */
    private static final String ACCESS_CONTROL_ALLOW_METHODS_HEADER = "Access-Control-Allow-Methods";

    /** The access control allow methods header. */
    private static final String ACCESS_CONTROL_ALLOW_METHODS_HEADER_VALUE = "GET,POST,PUT,DELETE";

    /** The access control allow header. */
    private static final String ACCESS_CONTROL_ALLOW_HEADER = "Access-Control-Allow-Headers";

    /** The access control allow methods header. */
    private static final String ACCESS_CONTROL_ALLOW_HEADER_VALUE = "Origin,X-Requested-With,Content-Type,Accept,Authorization,X-App-Identifier,X-Client,X-Version,X-New-Version,X-DeviceId";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ae) throws IOException, ServletException {
        LOGGER.info("Pre-authenticated entry point called. Rejecting access");
        if (request.getMethod().equals("OPTIONS")) {
            response.addHeader(ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, ACCESS_CONTROL_ALLOW_ORIGIN_HEADER_VALUE);
            response.addHeader(ACCESS_CONTROL_ALLOW_METHODS_HEADER, ACCESS_CONTROL_ALLOW_METHODS_HEADER_VALUE);
            response.addHeader(ACCESS_CONTROL_ALLOW_HEADER, ACCESS_CONTROL_ALLOW_HEADER_VALUE);
            response.sendError(HttpServletResponse.SC_OK, "OK");
        } else {
            response.addHeader(ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, ACCESS_CONTROL_ALLOW_ORIGIN_HEADER_VALUE);
            response.addHeader(ACCESS_CONTROL_ALLOW_METHODS_HEADER, ACCESS_CONTROL_ALLOW_METHODS_HEADER_VALUE);
            response.addHeader(ACCESS_CONTROL_ALLOW_HEADER, ACCESS_CONTROL_ALLOW_HEADER_VALUE);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }

    }
}
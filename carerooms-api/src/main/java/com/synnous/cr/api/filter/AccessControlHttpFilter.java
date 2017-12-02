package com.synnous.cr.api.filter;

import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The filter for the access control header.
 *
 * @author : Manos Papantonakos.
 */
public class AccessControlHttpFilter implements Filter {

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
    public void init(final FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
        if (StringUtils.isEmpty(((HttpServletResponse) servletResponse).getHeader(ACCESS_CONTROL_ALLOW_ORIGIN_HEADER))) {
            ((HttpServletResponse) servletResponse).addHeader(ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, ACCESS_CONTROL_ALLOW_ORIGIN_HEADER_VALUE);
            ((HttpServletResponse) servletResponse).addHeader(ACCESS_CONTROL_ALLOW_METHODS_HEADER, ACCESS_CONTROL_ALLOW_METHODS_HEADER_VALUE);
            ((HttpServletResponse) servletResponse).addHeader(ACCESS_CONTROL_ALLOW_HEADER, ACCESS_CONTROL_ALLOW_HEADER_VALUE);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

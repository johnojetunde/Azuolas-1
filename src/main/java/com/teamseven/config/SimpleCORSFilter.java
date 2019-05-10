package com.teamseven.config;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCORSFilter implements Filter {

    @Value("${headers.access.allowed-origins:*}")
    private String accessControlAllowedDomain;

    @Value("${headers.access.allowed-insecure-origin:true}")
    private boolean accessControlAllowedInsecureOrigin;

    private static final Logger logger = LoggerFactory.getLogger(SimpleCORSFilter.class);

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        String originHeader = request.getHeader("Origin");
        if (isOriginAllowed(originHeader)) {
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, originHeader);
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "POST, PUT, GET, OPTIONS, DELETE");
            response.setHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600");
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "x-requested-with,origin,accept,content-type,access-control-request-method,access-control-request-headers,authorization");
        }

        if (!RequestMethod.OPTIONS.name().equals(request.getMethod())) {
            try {
                chain.doFilter(new RequestWrapper((HttpServletRequest) req), res);
            } catch (IOException | ServletException e) {
                logger.error("Error thrown: ", e);
            }
        }
    }

    private boolean isOriginAllowed(String origin) {
        if (StringUtils.isBlank(origin)) {
            return false;
        }

        if ("*".equalsIgnoreCase(accessControlAllowedDomain)) {
            return true;
        }

        return accessControlAllowedDomain.contains(origin) && isEncryptionStatusAllowed(origin);
    }

    private boolean isEncryptionStatusAllowed(String origin) {
        if (accessControlAllowedInsecureOrigin) {
            return true;
        }

        return StringUtils.startsWithIgnoreCase(origin, "https");
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }
}

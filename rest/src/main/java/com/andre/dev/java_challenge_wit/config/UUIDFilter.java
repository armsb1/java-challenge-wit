package com.andre.dev.java_challenge_wit.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.io.IOException;
import java.util.UUID;


/**
 * The type Uuid filter.
 */
@Slf4j
public class UUIDFilter implements Filter {

    private static final String UUID_KEY = "requestUUID";

    @Override
    public void init(FilterConfig filterConfig){
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String uuid = UUID.randomUUID().toString();
        MDC.put(UUID_KEY, uuid);
        log.info("Generated UUID for request: {}", uuid);

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.remove(UUID_KEY);
        }
    }

    @Override
    public void destroy() {
    }
}
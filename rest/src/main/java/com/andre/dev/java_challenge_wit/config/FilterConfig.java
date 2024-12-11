package com.andre.dev.java_challenge_wit.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Filter config.
 */
@Configuration
public class FilterConfig {

    /**
     * Logging filter filter registration bean.
     *
     * @return the filter registration bean
     */
    @Bean
    public FilterRegistrationBean<UUIDFilter> loggingFilter() {
        FilterRegistrationBean<UUIDFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new UUIDFilter());
        registrationBean.addUrlPatterns("/api/*");
        return registrationBean;
    }
}

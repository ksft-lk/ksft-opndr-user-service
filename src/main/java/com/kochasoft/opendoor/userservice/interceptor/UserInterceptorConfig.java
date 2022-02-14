package com.kochasoft.opendoor.userservice.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class UserInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    UserInterceptor userInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor);
        registry.addInterceptor(userInterceptor).excludePathPatterns(
            "/v1/users/**/token",
            "/v1/users/uid/**",
            "/v1/users/id/**",
            "/v1/users/mobile/**",
            "/v1/users").pathMatcher(new AntPathMatcher());
    }
}

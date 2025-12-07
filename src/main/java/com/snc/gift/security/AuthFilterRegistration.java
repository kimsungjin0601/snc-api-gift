package com.snc.gift.security;

import com.cstify.common.provider.TokenProvider;
import com.cstify.common.service.RedisTokenService;
import com.cstify.common.service.TokenBlockService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@RequiredArgsConstructor
public class AuthFilterRegistration {

    private final SecurityUserService securityUserService;

    private final TokenProvider tokenProvider;

    private final RedisTokenService redisTokenService;

    private final TokenBlockService tokenBlockService;

    private final ObjectMapper objectMapper;

    @Bean
    @NullMarked
    public FilterRegistrationBean<SecurityLoginFilter> memberLoginFilterRegisterBean(AuthenticationManager authenticationManager) {
        FilterRegistrationBean<SecurityLoginFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(memberLoginFilter(authenticationManager));
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/api/auth/sign-in");
        filterRegistrationBean.setEnabled(true);
        return filterRegistrationBean;
    }

    @Bean
    public SecurityLoginFilter memberLoginFilter(AuthenticationManager authenticationManage) {
        SecurityLoginFilter filter = new SecurityLoginFilter(objectMapper, securityUserService);
		filter.setFilterProcessesUrl("/api/auth/sign-in");
		filter.setAuthenticationManager(authenticationManage);
		filter.setUsernameParameter("loginId");
		filter.setPasswordParameter("password");
		filter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
		filter.setAuthenticationFailureHandler(authenticationFailureHandler());
		return filter;
	}

    @Bean
    @NullMarked
    public FilterRegistrationBean<SecurityLoginFilter> adminLoginFilterRegisterBean(AuthenticationManager authenticationManager) {
        FilterRegistrationBean<SecurityLoginFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(adminLoginFilter(authenticationManager));
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.addUrlPatterns("/api/auth/sign-in/admin");
        filterRegistrationBean.setEnabled(true);
        return filterRegistrationBean;
    }

    @Bean
    public SecurityLoginFilter adminLoginFilter(AuthenticationManager authenticationManage) {
        SecurityLoginFilter filter = new SecurityLoginFilter(objectMapper, securityUserService);
        filter.setFilterProcessesUrl("/api/auth/sign-in/admin");
        filter.setAuthenticationManager(authenticationManage);
        filter.setUsernameParameter("loginId");
        filter.setPasswordParameter("password");
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        filter.setAuthenticationFailureHandler(authenticationFailureHandler());
        return filter;
    }

    @Bean
    @NullMarked
    public FilterRegistrationBean<SecurityLoginFilter> orderLoginFilterRegisterBean(AuthenticationManager authenticationManager) {
        FilterRegistrationBean<SecurityLoginFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(orderLoginFilter(authenticationManager));
        filterRegistrationBean.setOrder(3);
        filterRegistrationBean.addUrlPatterns("/api/auth/sign-in/order");
        filterRegistrationBean.setEnabled(true);
        return filterRegistrationBean;
    }

    @Bean
    public SecurityLoginFilter orderLoginFilter(AuthenticationManager authenticationManage) {
        SecurityLoginFilter filter = new SecurityLoginFilter(objectMapper, securityUserService);
        filter.setFilterProcessesUrl("/api/auth/sign-in/order");
        filter.setAuthenticationManager(authenticationManage);
        filter.setUsernameParameter("loginId");
        filter.setPasswordParameter("password");
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        filter.setAuthenticationFailureHandler(authenticationFailureHandler());
        return filter;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(securityUserService).passwordEncoder(passwordEncoder());
        return builder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthSuccessHandler(tokenProvider, redisTokenService, tokenBlockService, objectMapper);
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new AuthFailureHandler(objectMapper);
    }
}

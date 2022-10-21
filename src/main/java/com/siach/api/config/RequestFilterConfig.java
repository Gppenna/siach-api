package com.siach.api.config;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RequestFilterConfig extends OncePerRequestFilter {

    private final AppConfig appConfig;

    public RequestFilterConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, IOException {
        response.setHeader("Access-Control-Allow-Origin", appConfig.getAppUrl());
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, HEAD");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, withCredentials, Authorization,  Cache-Control, Content-Type, Origin, key");
        response.setHeader("Content-Type", "*");
        response.setHeader("withCredentials", "true");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        filterChain.doFilter(request, response);
    }
}

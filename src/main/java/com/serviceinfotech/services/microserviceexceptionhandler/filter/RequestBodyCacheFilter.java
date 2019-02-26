package com.serviceinfotech.services.microserviceexceptionhandler.filter;


import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RequestBodyCacheFilter extends OncePerRequestFilter {

    public static final List<String> VERBS = Arrays.asList("PUT", "POST", "PATCH", "DELETE");


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {

            if (wrapRequest((HttpServletRequest) request)) {

                // create cache
                RequestBodyWrapper wrapper = getInstance((HttpServletRequest) request);

                // set thread local
                RequestBodyThreadLocal.set(wrapper.getRequestBody());

                // continue
                chain.doFilter(wrapper, response);
            } else {
                chain.doFilter(request, response);
            }
        } finally {

            RequestBodyThreadLocal.clear();
        }
    }

    protected RequestBodyWrapper getInstance(HttpServletRequest request) throws IOException {
        return new RequestWrapper(request);
    }

    protected boolean wrapRequest(HttpServletRequest request) {
        return VERBS.contains(request.getMethod());
    }
}
package com.serviceinfotech.services.microserviceexceptionhandler.config;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MaintenanceInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ServletInputStream inputStream = request.getInputStream();
        System.out.println(
        IOUtils.toString(inputStream));
//        if (request.getMethod().equalsIgnoreCase(HttpMethod.POST.toString())) {
//            System.out.println("£££££");
//        }

        return true;

    }
}

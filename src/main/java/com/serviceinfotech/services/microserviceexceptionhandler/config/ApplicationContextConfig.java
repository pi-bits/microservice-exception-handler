package com.serviceinfotech.services.microserviceexceptionhandler.config;

import com.serviceinfotech.services.microserviceexceptionhandler.filter.RequestBodyCacheFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.logging.Filter;

@Configuration
public class ApplicationContextConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MaintenanceInterceptor());
}

    @Bean
    public FilterRegistrationBean someFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(someFilter());
        registration.addUrlPatterns("/v1/account/*");
//        registration.addInitParameter("paramName", "paramValue");
        registration.setName("someFilter");
//        registration.setOrder(1);
        return registration;
    }

    public javax.servlet.Filter someFilter() {
        return new RequestBodyCacheFilter();
    }



}

package com.serviceinfotech.services.microserviceexceptionhandler.health;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.EndpointExtension;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.boot.actuate.health.HealthEndpoint;


//@Endpoint( id = "dbHealth")
@Endpoint( id = "health")
public class HealthIndicator {
//    private final HealthEndpoint healthEndpoint;
//
//    public HealthIndicator(HealthEndpoint healthEndpoint) {
//        this.healthEndpoint = healthEndpoint;
//    }

    @ReadOperation
    public String hello() {
        return "hello health";
    }


}

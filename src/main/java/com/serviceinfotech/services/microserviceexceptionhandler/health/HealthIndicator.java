package com.serviceinfotech.services.microserviceexceptionhandler.health;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.health.Health;


@Endpoint( id = "health")
public class HealthIndicator {

    @ReadOperation
    public WebEndpointResponse<Health> health() {
        return new WebEndpointResponse<Health>(Health.outOfService().build());
    }


}

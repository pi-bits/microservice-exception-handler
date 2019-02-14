package com.serviceinfotech.services.microserviceexceptionhandler.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("client")
public class Client {
    private String type;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

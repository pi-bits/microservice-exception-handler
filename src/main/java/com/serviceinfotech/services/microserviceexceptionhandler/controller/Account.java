package com.serviceinfotech.services.microserviceexceptionhandler.controller;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Account {
    @JsonProperty
    private String accountNumber;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}

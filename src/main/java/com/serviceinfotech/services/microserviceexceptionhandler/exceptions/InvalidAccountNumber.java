package com.serviceinfotech.services.microserviceexceptionhandler.exceptions;

public class InvalidAccountNumber extends RuntimeException {

    public InvalidAccountNumber(Integer accountNumber) {
        super(String.format("Account number %s is Invalid",accountNumber));
    }
}

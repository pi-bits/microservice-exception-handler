package com.serviceinfotech.services.microserviceexceptionhandler.exceptions;

public class AccountNumberNotFound extends RuntimeException {

    public AccountNumberNotFound(Integer accountNumber) {
        super(String.format("error fetching account details for account number : %s, please contact service desk",accountNumber));
    }
}

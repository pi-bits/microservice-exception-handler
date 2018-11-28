package com.serviceinfotech.services.microserviceexceptionhandler.service;

import com.serviceinfotech.services.microserviceexceptionhandler.exceptions.AccountNumberNotFound;
import com.serviceinfotech.services.microserviceexceptionhandler.exceptions.InvalidAccountNumber;
import com.serviceinfotech.services.microserviceexceptionhandler.model.AccountDetails;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class AccountService {

    private final RestTemplate restTemplate;

    private List<AccountDetails> listOfAccounts = Arrays.asList(
            new AccountDetails("Prashant Naik", "201 Graton gate", 07654257553),
            new AccountDetails("Thomas Cook", "sodor", 9876543),
            new AccountDetails("Percy", "Knpfor", 456789553));

    public AccountService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.errorHandler(new RestResponseErrorHandler()).build();
    }

    public AccountDetails getAccountNumber(Integer accountNumber) {
        if (isValidAccountNumber(accountNumber)) {
            throw new InvalidAccountNumber(accountNumber);
        }

        try {
            ResponseEntity<AccountDetails> forEntity = restTemplate.getForEntity("/application/details/" + accountNumber, AccountDetails.class);
            return forEntity.getBody();
        } catch (RequestException ex) {
            throw ex;
        }

    }

    private Boolean isValidAccountNumber(Integer accountNumber) {
        return accountNumber < 0;
    }
}

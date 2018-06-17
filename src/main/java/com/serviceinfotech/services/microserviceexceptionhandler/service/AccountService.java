package com.serviceinfotech.services.microserviceexceptionhandler.service;

import com.serviceinfotech.services.microserviceexceptionhandler.exceptions.AccountNumberNotFound;
import com.serviceinfotech.services.microserviceexceptionhandler.exceptions.InvalidAccountNumber;
import com.serviceinfotech.services.microserviceexceptionhandler.model.AccountDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AccountService {
    private List<AccountDetails> listOfAccounts = Arrays.asList(
            new AccountDetails("Prashant Naik", "201 Graton gate", 07654257553),
            new AccountDetails("Thomas Cook", "sodor", 9876543),
            new AccountDetails("Percy", "Knpfor", 456789553));

    public AccountDetails getAccountNumber(Integer accountNumber) {
        if (isValidAccountNumber(accountNumber)) {
            throw new InvalidAccountNumber(accountNumber);
        }

        try {

            return listOfAccounts.get(accountNumber);
        } catch (RuntimeException ex) {
            throw new AccountNumberNotFound(accountNumber);
        }

    }

    private Boolean isValidAccountNumber(Integer accountNumber) {
        return accountNumber < 0;
    }
}

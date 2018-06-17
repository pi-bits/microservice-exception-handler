package com.serviceinfotech.services.microserviceexceptionhandler.service;

import com.serviceinfotech.services.microserviceexceptionhandler.exceptions.AccountNumberNotFound;
import com.serviceinfotech.services.microserviceexceptionhandler.exceptions.InvalidAccountNumber;
import com.serviceinfotech.services.microserviceexceptionhandler.model.AccountDetails;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Test
    public void shouldGetAccountDetails() throws Exception {
        AccountDetails accountDetails = accountService.getAccountNumber(0);
        assertThat(accountDetails.getAccountHolderAddress(), Is.is("201 Graton gate"));
        assertThat(accountDetails.getAccountHolderName(), Is.is("Prashant Naik"));
        assertThat(accountDetails.getPhoneNumber(), Is.is(1051811691));
    }

    @Test(expected = InvalidAccountNumber.class)
    public void shouldThrowInvalidAccountNumberException() throws Exception {
        accountService.getAccountNumber(-13456789);
    }

    @Test(expected = AccountNumberNotFound.class)
    public void shouldThrowAccountNumberNotFoundException() throws Exception {
        accountService.getAccountNumber(23456789);
    }
}
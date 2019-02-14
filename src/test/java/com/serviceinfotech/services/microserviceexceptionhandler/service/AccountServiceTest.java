package com.serviceinfotech.services.microserviceexceptionhandler.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serviceinfotech.services.microserviceexceptionhandler.MicroserviceExceptionHandlerApplication;
import com.serviceinfotech.services.microserviceexceptionhandler.config.RestClientConfig;
import com.serviceinfotech.services.microserviceexceptionhandler.exceptions.AccountNumberNotFound;
import com.serviceinfotech.services.microserviceexceptionhandler.exceptions.InvalidAccountNumber;
import com.serviceinfotech.services.microserviceexceptionhandler.model.AccountDetails;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@RestClientTest(AccountService.class)
@ContextConfiguration(classes = MicroserviceExceptionHandlerApplication.class)
public class AccountServiceTest {

    @Autowired
    MockRestServiceServer mockRestServiceServer;


    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    AccountService accountService;



    @Test
    public void shouldGetAccountDetails() throws Exception {

        String detailsString =
                objectMapper.writeValueAsString(new AccountDetails("Prashant Naik", "201 Graton gate", 07654257553));

        this.mockRestServiceServer.expect(MockRestRequestMatchers.requestTo("/application/details/0"))
                .andRespond(MockRestResponseCreators.withSuccess(detailsString, MediaType.APPLICATION_JSON));

        AccountDetails accountDetails = accountService.getAccountNumber(0);

        this.mockRestServiceServer.verify();

        assertThat(accountDetails.getAccountHolderAddress(), Is.is("201 Graton gate"));
        assertThat(accountDetails.getAccountHolderName(), Is.is("Prashant Naik"));
        assertThat(accountDetails.getPhoneNumber(), Is.is(1051811691));
    }


    @Test(expected = RequestException.class)
    public void shouldHandle401() throws Exception {


        this.mockRestServiceServer.expect(MockRestRequestMatchers.requestTo("/application/details/0"))
                .andRespond(MockRestResponseCreators.withUnauthorizedRequest());

        AccountDetails accountDetails = accountService.getAccountNumber(0);

        this.mockRestServiceServer.verify();

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
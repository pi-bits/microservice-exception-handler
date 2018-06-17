package com.serviceinfotech.services.microserviceexceptionhandler.controller;

import com.serviceinfotech.services.microserviceexceptionhandler.exceptions.AccountNumberNotFound;
import com.serviceinfotech.services.microserviceexceptionhandler.exceptions.InvalidAccountNumber;
import com.serviceinfotech.services.microserviceexceptionhandler.model.AccountDetails;
import com.serviceinfotech.services.microserviceexceptionhandler.service.AccountService;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class AccountControllerIT {
    
    @Autowired
    MockMvc mockMvc;

   @Autowired
    AccountService accountService;

    @Test
    public void shouldReturnAccountDetailsWithStatusOK() throws Exception {
        mockMvc.perform(get("/v1/account/0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountHolderName", Is.is("Prashant Naik")))
                .andExpect(jsonPath("$.accountHolderAddress", Is.is("201 Graton gate")))
                .andExpect(jsonPath("$.phoneNumber", Is.is(1051811691)));
    }

    @Test
    public void shouldReturnErrorReponseWithStatus400() throws Exception {

        mockMvc.perform(get("/v1/account/-3454"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", Is.is(400)))
                .andExpect(jsonPath("$.errorMessage", Is.is("Account number -3454 is Invalid")));

    }

    @Test
    public void shouldReturnErrorReponseWithStatus500() throws Exception {
        mockMvc.perform(get("/v1/account/300"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.errorCode", Is.is(500)))
                .andExpect(jsonPath("$.errorMessage", Is.is("error fetching account details for account number : 300, please contact service desk")));

    }
}
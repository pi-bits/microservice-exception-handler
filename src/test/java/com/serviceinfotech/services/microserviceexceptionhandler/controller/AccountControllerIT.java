package com.serviceinfotech.services.microserviceexceptionhandler.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureWireMock(port = 9999, stubs = "classpath:/stubs")
@ActiveProfiles("local")
public class AccountControllerIT {

    @Autowired
    MockMvc mockMvc;


    @Test
    public void shouldReturnAccountDetailsWithStatusOK() throws Exception {
        mockMvc.perform(get("/v1/account/0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountHolderName", Is.is("Prashant Naik")))
                .andExpect(jsonPath("$.accountHolderAddress", Is.is("201 Graton gate")))
                .andExpect(jsonPath("$.phoneNumber", Is.is(1051811691)));
    }

    @Test
    public void shouldSaveAccountDetailsWithStatusOK() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Account value = new Account();
        value.setAccountNumber("345678");

        mockMvc.perform(post("/v1/account/0")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(value)))
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
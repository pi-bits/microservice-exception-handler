package com.serviceinfotech.services.microserviceexceptionhandler.controller;

import com.serviceinfotech.services.microserviceexceptionhandler.model.AccountDetails;
import com.serviceinfotech.services.microserviceexceptionhandler.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping(value = "/v1/account/{account_number}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDetails> getAccount(@PathVariable("account_number") Integer accountNumber){
        return new ResponseEntity<AccountDetails>(accountService.getAccountNumber(accountNumber), HttpStatus.OK);
    }

    @PostMapping(value = "/v1/account/{account_number}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDetails> getAccount(@PathVariable("account_number") Integer accountNumber, @RequestBody Account account){
        return new ResponseEntity<AccountDetails>(accountService.save(accountNumber,account), HttpStatus.OK);
    }
}

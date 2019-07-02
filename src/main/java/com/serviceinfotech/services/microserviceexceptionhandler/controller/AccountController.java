package com.serviceinfotech.services.microserviceexceptionhandler.controller;

import com.serviceinfotech.services.microserviceexceptionhandler.model.AccountDetails;
import com.serviceinfotech.services.microserviceexceptionhandler.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.Response;

@RestController
public class AccountController {

    private static final Logger LOG = LoggerFactory.getLogger(AccountController.class.getName());

    @Autowired
    private RestTemplate restTemplate;

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

    @RequestMapping("/")
    public String home() {
        LOG.info( "you called home");
        return "Hello World";
    }

    @RequestMapping("/callhome")
    public String callHome() {
        LOG.info( "calling home");
        return restTemplate.getForObject("http://localhost:8080", String.class);
    }

}

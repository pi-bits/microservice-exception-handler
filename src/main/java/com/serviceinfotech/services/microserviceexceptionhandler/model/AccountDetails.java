package com.serviceinfotech.services.microserviceexceptionhandler.model;

import java.io.Serializable;

public class AccountDetails implements Serializable {

    private String accountHolderName;
    private String accountHolderAddress;
    private Integer phoneNumber;


    public AccountDetails(String accountHolderName, String accountHolderAddress, Integer phoneNumber) {
        this.accountHolderName = accountHolderName;
        this.accountHolderAddress = accountHolderAddress;
        this.phoneNumber = phoneNumber;
    }

    public AccountDetails() {
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getAccountHolderAddress() {
        return accountHolderAddress;
    }

    public void setAccountHolderAddress(String accountHolderAddress) {
        this.accountHolderAddress = accountHolderAddress;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }




}

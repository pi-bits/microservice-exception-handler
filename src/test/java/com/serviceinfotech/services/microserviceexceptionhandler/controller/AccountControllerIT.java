package com.serviceinfotech.services.microserviceexceptionhandler.controller;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.jayway.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureWireMock(port = 9999, stubs = "classpath:/stubs")
@ActiveProfiles("local")
public class AccountControllerIT {

    @Autowired
    MockMvc mockMvc;


    @BeforeClass
    public static void setup() {
        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = Integer.valueOf(8080);
        } else {
            RestAssured.port = Integer.valueOf(port);
        }

        String basePath = System.getProperty("server.base");
        if (basePath == null) {
            basePath = "/msh/";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("server.host");
        if (baseHost == null) {
            baseHost = "http://localhost";
        }
        baseURI = baseHost;

    }

    @Test
    public void shouldReturnAccountDetailsWithStatusOK() throws Exception {
        given().when().get("/v1/account/0")
                .then()
                .statusCode(200)
                .body("accountHolderName", equalTo("Prashant Naik"))
                .body("accountHolderAddress", equalTo("201 Graton gate"))
                .body("phoneNumber", equalTo(1051811691));

    }

    @Test
    public void shouldSaveAccountDetailsWithStatusOK() throws Exception {
        Account account = new Account();
        account.setAccountNumber("345678");

        given().contentType("application/json")
                .body(account)
                .when()
                .post("/v1/account/0")
                .then()
                .statusCode(200)
                .body("accountHolderName", equalTo("Prashant Naik"))
                .body("accountHolderAddress", equalTo("201 Graton gate"))
                .body("phoneNumber", equalTo(1051811691));
    }


    @Test
    public void shouldReturnErrorReponseWithStatus400() throws Exception {

        given()
                .when()
                .get("/v1/account/-3454")
                .then()
                .statusCode(400)
                .body("errorCode", equalTo(400))
                .body("errorMessage", equalTo("Account number -3454 is Invalid"));
    }

    @Test
    public void shouldReturnErrorReponseWithStatus500() throws Exception {

        given()
                .when()
                .get("/v1/account/300")
                .then()
                .statusCode(500)
                .body("errorCode", equalTo(500))
                .body("errorMessage", equalTo("error fetching account details for account number : 300, please contact service desk"));

    }
}
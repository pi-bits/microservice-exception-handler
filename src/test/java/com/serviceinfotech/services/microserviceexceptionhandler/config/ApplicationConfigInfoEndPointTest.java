package com.serviceinfotech.services.microserviceexceptionhandler.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class ApplicationConfigInfoEndPointTest {
    @Test
    public void shoudLoadInfoEndPoint() throws Exception {
        given()
                .when()
                .get("http://localhost:8080/msh/info")
                .then().assertThat().statusCode(200);

    }
}
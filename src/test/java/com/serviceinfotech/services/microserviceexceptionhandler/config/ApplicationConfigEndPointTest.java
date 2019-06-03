package com.serviceinfotech.services.microserviceexceptionhandler.config;

import com.serviceinfotech.services.microserviceexceptionhandler.health.HealthIndicator;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class ApplicationConfigEndPointTest {

    @SpyBean
    HealthIndicator healthIndicator;
    @Test
    public void shoudLoadInfoEndPoint() throws Exception {
        given()
                .when()
                .get("http://localhost:8080/info")
                .then().assertThat().statusCode(200);

    }

    @Test
    public void shoudLoadHealthEndPoint() throws Exception {

        given()
                .when()
                .get("http://localhost:8080/msh/health")
                .then().assertThat().statusCode(200);

    }

    @Test
    public void shoudLoadHealthEndPointByActuator() throws Exception {

        given()
                .when()
                .get("http://localhost:8080/msh/health")
                .then().assertThat().statusCode(200).content(Is.is("hello health"));
        Mockito.verify(healthIndicator).health();

    }

}
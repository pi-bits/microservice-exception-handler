package com.serviceinfotech.services.microserviceexceptionhandler.config;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@ActiveProfiles("local")
public class ApplicationConfigTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ApplicationConfig applicationConfig;

    @Test
    public void contextLoads() throws Exception {
        assertThat(applicationConfig.getEndpoint(), Is.is("http://localhost:9999/application/details/"));
    }
}
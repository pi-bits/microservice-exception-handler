package com.serviceinfotech.services.microserviceexceptionhandler.config;

import org.hamcrest.collection.IsMapContaining;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
@ActiveProfiles("local")
public class NestedConfigTest {
    @Autowired
    ApplicationConfig applicationConfig;

    @Test
    public void shouldLoadConfig() throws Exception {

        assertThat(applicationConfig.getClientId(), Is.is("345678-56789-456789-456789"));
        Map<String, Client> actualClients = applicationConfig.getClients();

        Client client = actualClients.get("AGG0001");
        assertThat(client.getCode(),Is.is("12345"));
        assertThat(client.getType(),Is.is("INT"));

        client = actualClients.get("AGG0002");
        assertThat(client.getCode(),Is.is("3456789"));
        assertThat(client.getType(),Is.is("OFI"));


    }
}

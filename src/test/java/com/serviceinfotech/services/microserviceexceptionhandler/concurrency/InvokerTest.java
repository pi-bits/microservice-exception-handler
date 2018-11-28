package com.serviceinfotech.services.microserviceexceptionhandler.concurrency;

import org.junit.Test;

import java.util.concurrent.Executor;

public class InvokerTest {

    @Test
    public void shouldInvoke() throws Exception {
        Executor invoker = new Invoker();
        invoker.execute(() -> {

        });
    }
}


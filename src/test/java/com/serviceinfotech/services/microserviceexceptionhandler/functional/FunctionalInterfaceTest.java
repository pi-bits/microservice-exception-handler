package com.serviceinfotech.services.microserviceexceptionhandler.functional;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.BiFunction;

public class FunctionalInterfaceTest {
    @Test
    public void shouldSquareAValue() throws Exception {
        Square square = val -> val * val;
        Assert.assertThat(square.square(12), Is.is(144));
    }

    @Test
    public void shouldMultiply() throws Exception {
        BiFunction<Integer, Integer, Integer> biFunction = (x, y) -> x * y;
        Assert.assertThat(biFunction.apply(12,12), Is.is(144));
    }
}

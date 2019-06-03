package com.serviceinfotech.services.microserviceexceptionhandler.utils;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ClockProvider;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.junit.Assert.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("local")
public class DateUtilsIntegrationTest {

    @Autowired
    private DateUtils dateUtils;

    @MockBean
    private ClockProvider clockProvider;


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldCalculateRemainingYears_withFixedClock() {
        String instantExpected = "2017-12-22T10:15:30Z";
        Clock clock = Clock.fixed(Instant.parse(instantExpected), ZoneId.of("Europe/London"));
        Mockito.when(clockProvider.getClock()).thenReturn(clock);
        assertThat(dateUtils.calculateNumberOfYears(LocalDate.of(2025, 12, 1)), Is.is(7l));

    }

    @Test
    public void shouldCalculateRemainingYears_withSystemClock() {
        Clock clock = Clock.systemDefaultZone();
        Mockito.when(clockProvider.getClock()).thenReturn(clock);
        assertThat(dateUtils.calculateNumberOfYears(LocalDate.of(2025, 12, 1)), Is.is(6l));

    }
}
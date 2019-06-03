package com.serviceinfotech.services.microserviceexceptionhandler.utils;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;

public class DateUtilsTest {

    @Test
    public void shouldCalculateMaxDate() {
        DateUtils dateUtils = new DateUtils(() -> Clock.systemDefaultZone());
        List<LocalDate> localDates = Arrays.asList(
                LocalDate.of(2015, 1, 1),
                LocalDate.of(2016, 2, 1),
                LocalDate.of(2017, 5, 1),
                LocalDate.of(2018, 7, 1),
                LocalDate.of(2019, 9, 1),
                LocalDate.of(2014, 10, 1),
                LocalDate.of(2013, 2, 1),
                LocalDate.of(2015, 1, 1),
                LocalDate.of(2012, 8, 1),
                LocalDate.of(2011, 9, 1),
                LocalDate.of(2009, 4, 1),
                LocalDate.of(2008, 2, 1)
        );
        LocalDate maxDate = dateUtils.calculateMaxDate(localDates);
        Assert.assertThat(maxDate.getYear(), Is.is(2019));
        Assert.assertThat(maxDate.getMonthValue(), Is.is(9));
        Assert.assertThat(maxDate.getDayOfMonth(), Is.is(1));
    }

    @Test
    public void shouldGetAgeAsOfToday() {
        DateUtils dateUtils = new DateUtils(() -> Clock.systemDefaultZone());
        Period age = dateUtils.calculateAge( LocalDate.of(1982, 11, 2));
        Assert.assertThat(age.getYears(), Is.is(36));
        Assert.assertThat(age.getMonths(), Is.is(7));
        Assert.assertThat(age.getDays(), Is.is(1   ));
    }
}
package com.serviceinfotech.services.microserviceexceptionhandler.utils;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;

import static java.time.Clock.systemDefaultZone;
import static java.time.LocalDate.of;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;

public class DateUtilsTest {

    @Test
    public void shouldCalculateMaxDate() {
        DateUtils dateUtils = new DateUtils(() -> systemDefaultZone());
        List<LocalDate> localDates = asList(
                of(2015, 1, 1),
                of(2016, 2, 1),
                of(2017, 5, 1),
                of(2018, 7, 1),
                of(2019, 9, 1),
                of(2014, 10, 1),
                of(2013, 2, 1),
                of(2015, 1, 1),
                of(2012, 8, 1),
                of(2011, 9, 1),
                of(2009, 4, 1),
                of(2008, 2, 1)
        );
        LocalDate maxDate = dateUtils.calculateMaxDate(localDates);
        Assert.assertThat(maxDate.getYear(), is(2019));
        Assert.assertThat(maxDate.getMonthValue(), is(9));
        Assert.assertThat(maxDate.getDayOfMonth(), is(1));
    }

    @Test
    public void shouldGetAgeAsOfToday() {
        DateUtils dateUtils = new DateUtils(() -> systemDefaultZone());
        Period age = dateUtils.calculateAge(of(1982, 11, 2));
        Assert.assertThat(age.getYears(), is(36));
        Assert.assertThat(age.getMonths(), is(7));
        Assert.assertThat(age.getDays(), is(1));
    }
}
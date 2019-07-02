package com.serviceinfotech.services.microserviceexceptionhandler.utils;

import org.springframework.stereotype.Service;

import javax.validation.ClockProvider;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import static java.time.LocalDate.now;
import static java.time.Period.between;
import static java.time.temporal.ChronoUnit.YEARS;
import static java.util.Comparator.comparing;

@Service
public class DateUtils {
    private ClockProvider clockProvider;

    public DateUtils(ClockProvider clockProvider) {
        this.clockProvider = clockProvider;
    }

    public Long calculateNumberOfYears(LocalDate localDate) {
        return YEARS.between(now(clockProvider.getClock()), localDate);
    }

    public LocalDate calculateMaxDate(List<LocalDate> dates) {
        return dates.stream()
                .max(comparing(LocalDate::toEpochDay))
                .get();
    }

    public Period calculateAge(LocalDate date) {
        return between(date, now());
    }
}

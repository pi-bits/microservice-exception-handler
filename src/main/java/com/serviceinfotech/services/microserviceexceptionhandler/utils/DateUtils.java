package com.serviceinfotech.services.microserviceexceptionhandler.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ClockProvider;
import java.time.Clock;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

@Service
public class DateUtils {
    private ClockProvider clockProvider;

    public DateUtils(ClockProvider clockProvider) {
        this.clockProvider = clockProvider;
    }

    public Long calculateNumberOfYears(LocalDate localDate) {
        return ChronoUnit.YEARS.between(LocalDate.now(clockProvider.getClock()), localDate);
    }

    public LocalDate calculateMaxDate(List<LocalDate> dates) {
       return dates.stream()
                .max( Comparator.comparing( LocalDate::toEpochDay ) )
                .get();
    }

    public Period calculateAge(LocalDate date) {
     return Period.between(date, LocalDate.now());
    }
}

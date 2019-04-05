package com.serviceinfotech.services.microserviceexceptionhandler.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ClockProvider;
import java.time.Clock;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class DateUtils {
    private ClockProvider clockProvider;

    public DateUtils(ClockProvider clockProvider) {
        this.clockProvider = clockProvider;
    }

    public Long calculateNumberOfYears(LocalDate localDate) {
        return ChronoUnit.YEARS.between(LocalDate.now(clockProvider.getClock()), localDate);
    }
}

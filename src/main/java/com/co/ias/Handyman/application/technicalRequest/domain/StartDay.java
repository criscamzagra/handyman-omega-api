package com.co.ias.Handyman.application.technicalRequest.domain;

import org.apache.commons.lang3.Validate;

import java.time.LocalDateTime;
import java.util.Date;

public class StartDay {

    private final LocalDateTime value;

    public StartDay(LocalDateTime value) {
        Validate.notNull(value, "Start Day can't be null");
        this.value = value;
    }

    public LocalDateTime getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}

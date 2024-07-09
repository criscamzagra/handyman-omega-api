package com.co.ias.Handyman.application.technicalRequest.domain;

import java.time.LocalDateTime;
import java.util.Date;

public class EndDay {

    private final LocalDateTime value;

    public EndDay(LocalDateTime value) {
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

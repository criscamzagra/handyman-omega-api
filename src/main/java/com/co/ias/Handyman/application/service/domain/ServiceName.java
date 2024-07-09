package com.co.ias.Handyman.application.service.domain;

import org.apache.commons.lang3.Validate;

public class ServiceName {

    private final String value;

    public ServiceName(String value) {
        Validate.notNull(value, "Service Name can't be null");
        Validate.isTrue( value.length() < 40, "Service Name can't be longer than 40 characters" );
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}

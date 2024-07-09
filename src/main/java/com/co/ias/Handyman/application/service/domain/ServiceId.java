package com.co.ias.Handyman.application.service.domain;

import org.apache.commons.lang3.Validate;

public class ServiceId {

    private final Integer value;

    public ServiceId(Integer value) {
        Validate.notNull(value, "Service ID can't be null");
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}

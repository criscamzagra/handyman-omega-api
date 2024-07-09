package com.co.ias.Handyman.application.technical.domain;

import org.apache.commons.lang3.Validate;

public class TechnicalId {

    private final Integer value;

    public TechnicalId(Integer value) {
        Validate.notNull(value, "Technical ID can't be null");
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

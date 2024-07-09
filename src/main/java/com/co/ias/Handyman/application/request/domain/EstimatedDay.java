package com.co.ias.Handyman.application.request.domain;

import org.apache.commons.lang3.Validate;

public class EstimatedDay {

    private final String value;

    public EstimatedDay(String value) {
        Validate.notNull(value, "Estimated Day can't be null");
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

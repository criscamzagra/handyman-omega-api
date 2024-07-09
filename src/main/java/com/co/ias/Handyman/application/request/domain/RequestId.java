package com.co.ias.Handyman.application.request.domain;

import org.apache.commons.lang3.Validate;

public class RequestId {

    private final Integer value;

    public RequestId(Integer value) {
        Validate.notNull(value, "Request ID can't be null");
        Validate.isTrue( value < 99999999, "Product ID can't be longer than 10 characters" );
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

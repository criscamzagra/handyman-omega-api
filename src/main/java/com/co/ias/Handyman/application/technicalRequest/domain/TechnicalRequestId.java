package com.co.ias.Handyman.application.technicalRequest.domain;

import org.apache.commons.lang3.Validate;

public class TechnicalRequestId {

    private final Integer value;

    public TechnicalRequestId(Integer value) {

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



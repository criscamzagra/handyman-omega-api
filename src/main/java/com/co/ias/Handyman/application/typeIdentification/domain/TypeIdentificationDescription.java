package com.co.ias.Handyman.application.typeIdentification.domain;

import org.apache.commons.lang3.Validate;

public class TypeIdentificationDescription {

    private final String value;

    public TypeIdentificationDescription(String value) {
        Validate.notNull(value, "Description of Type Identification can't be null");
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

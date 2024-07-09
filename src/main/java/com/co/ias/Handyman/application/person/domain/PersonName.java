package com.co.ias.Handyman.application.person.domain;

import org.apache.commons.lang3.Validate;

public class PersonName {

    private final String value;

    public PersonName(String value) {
        Validate.notNull(value, "Person Name can't be null");
        Validate.isTrue( value.length() < 40, "Person Name can't be longer than 40 characters" );
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

package com.co.ias.Handyman.application.person.domain;

import org.apache.commons.lang3.Validate;

public class PersonIdentification {

    private final String value;

    public PersonIdentification(String value) {
        Validate.notNull(value, "Person Identification can't be null");
        Validate.isTrue( value.length() < 99999999, "Person Identification can't be longer than 10 characters" );
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

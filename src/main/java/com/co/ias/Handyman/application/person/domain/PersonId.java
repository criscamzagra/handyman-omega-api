package com.co.ias.Handyman.application.person.domain;


import org.apache.commons.lang3.Validate;

public class PersonId {

    private final Integer value;

    public PersonId(Integer value) {
        Validate.notNull(value, "Person ID can't be null");
        Validate.isTrue( value < 99999999, "Person ID can't be longer than 10 characters" );
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

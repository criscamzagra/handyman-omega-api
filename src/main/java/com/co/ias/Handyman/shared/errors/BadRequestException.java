package com.co.ias.Handyman.shared.errors;

public class BadRequestException  extends RuntimeException {


    public BadRequestException(String detail) {
        super(detail);
    }

}

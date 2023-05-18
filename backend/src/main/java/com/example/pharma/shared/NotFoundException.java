package com.example.pharma.shared;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {

    public NotFoundException(String message) {
        super(false, HttpStatus.NOT_FOUND, message, ExceptionCode.NOT_FOUND);
    }
}

package com.tosin.investire.commons.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvestireException  extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String message;

    public InvestireException(HttpStatus httpStatus, String message) {

        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }


}

package com.jpmc.carinventorymanagement.exception;

import lombok.Getter;

@Getter
public class CarNotFoundException extends RuntimeException {
    private String message;
    private String errorCode;

    public CarNotFoundException() {
        super();
    }

    public CarNotFoundException(final String message, final String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

}

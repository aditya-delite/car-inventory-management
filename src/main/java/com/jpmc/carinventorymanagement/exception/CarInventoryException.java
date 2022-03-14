package com.jpmc.carinventorymanagement.exception;

import lombok.Getter;

@Getter
public class CarInventoryException extends RuntimeException {
    private String errorCode;
    private String message;

    public CarInventoryException() {
        super();
    }

    public CarInventoryException(final String message) {
        super(message);
    }

    public CarInventoryException(final String message, final String errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }
}

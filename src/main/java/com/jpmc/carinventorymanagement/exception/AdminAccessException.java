package com.jpmc.carinventorymanagement.exception;

import lombok.Getter;

@Getter
public class AdminAccessException extends RuntimeException {
    private String message;
    private String errorCode;

    public AdminAccessException() {
        super();
    }

    public AdminAccessException(String message, String errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }
}

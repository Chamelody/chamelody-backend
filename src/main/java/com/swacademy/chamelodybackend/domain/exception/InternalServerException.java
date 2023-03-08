package com.swacademy.chamelodybackend.domain.exception;

public class InternalServerException extends RuntimeException {
    public InternalServerException() {
        super();
    }

    public InternalServerException(String message) {
        super(message);
    }

    public InternalServerException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

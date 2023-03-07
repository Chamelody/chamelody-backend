package com.swacademy.chamelodybackend.domain.exception;

public class InternalPersistenceException extends RuntimeException {
    public InternalPersistenceException() {
        super();
    }

    public InternalPersistenceException(String message) {
        super(message);
    }

    public InternalPersistenceException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

package com.swacademy.chamelodybackend.domain.exception;

/**
 * <p> Capsuling several internal persistence exception. </p>
 * <p> Such as {@link org.springframework.dao.PessimisticLockingFailureException PessimisticLockingFailureException},
 * {@link org.springframework.dao.OptimisticLockingFailureException OptimisticLockingFailureException},
 * {@link org.springframework.dao.DataIntegrityViolationException DataIntegrityViolationException} etc. </p>
 */
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

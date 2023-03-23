package com.swacademy.chamelodybackend.aop;

import com.swacademy.chamelodybackend.domain.exception.InternalPersistenceException;
import com.swacademy.chamelodybackend.domain.exception.InternalServerException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class ExceptionHandlerAspect {

    @Around("@annotation(PersistenceExceptionHandler)")
    public Object persistenceExceptionHandler(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (InvalidDataAccessApiUsageException | DataRetrievalFailureException |
                 EntityNotFoundException | DuplicateKeyException illegalArgumentException) {
            throw new IllegalArgumentException(illegalArgumentException.getMessage() +
                    ": " + illegalArgumentException.getClass());
        } catch (DataAccessException | PersistenceException dataAccessException) {
            throw new InternalPersistenceException(dataAccessException.getMessage() +
                    ": " + dataAccessException.getClass());
        }
    }

    @Around("@annotation(InternalServerExceptionHandler)")
    public Object internalServerExceptionHandler(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (InternalPersistenceException internalPersistenceException) {
            throw new InternalServerException(internalPersistenceException.getMessage() +
                    ": " + internalPersistenceException.getClass());
        }
    }

}

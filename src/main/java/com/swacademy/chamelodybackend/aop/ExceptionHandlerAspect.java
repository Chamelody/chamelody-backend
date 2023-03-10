package com.swacademy.chamelodybackend.aop;

import com.swacademy.chamelodybackend.domain.exception.InternalPersistenceException;
import jakarta.persistence.EntityNotFoundException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExceptionHandlerAspect {

    @Around("@annotation(PersistenceExceptionHandler)")
    public Object persistenceExceptionHandler(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (InvalidDataAccessApiUsageException | DataRetrievalFailureException |
                 EntityNotFoundException | DuplicateKeyException illegalArgumentException) {
            throw new IllegalArgumentException(illegalArgumentException.getMessage());
        } catch (DataAccessException dataAccessException) {
            throw new InternalPersistenceException(dataAccessException.getMessage());
        }
    }


}

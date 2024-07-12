package com.paldomoa.common.exception;

import lombok.Getter;

@Getter
public class ExpiredPeriodJwtException extends AuthException {

    public ExpiredPeriodJwtException(final ExceptionData exceptionData) {
        super(exceptionData);
    }
}

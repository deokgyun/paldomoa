package com.paldomoa.common.exception;

import lombok.Getter;

@Getter
public class ExpiredJwtException extends AuthException {

    public ExpiredJwtException(final ExceptionData exceptionData) {
        super(exceptionData);
    }

}

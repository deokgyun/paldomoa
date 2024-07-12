package com.paldomoa.common.exception;

import lombok.Getter;

@Getter
public class InvalidJwtException extends AuthException {

    public InvalidJwtException(ExceptionData exceptionData) {
        super(exceptionData);
    }
}

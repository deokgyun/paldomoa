package com.paldomoa.common.exception;

import lombok.Getter;

@Getter
public class RefreshTokenException extends AuthException {

    public RefreshTokenException(ExceptionData exceptionData) {
        super(exceptionData);
    }
}

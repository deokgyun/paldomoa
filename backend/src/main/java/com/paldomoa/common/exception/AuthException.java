package com.paldomoa.common.exception;

import lombok.Getter;

@Getter
public class AuthException extends RuntimeException {

    private final int code;
    private final String message;

    public AuthException(ExceptionData exceptionData) {
        this.code = exceptionData.getCode();
        this.message = exceptionData.getMessage();
    }
}

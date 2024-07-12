package com.paldomoa.common.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {

    private final int code;
    private final String message;

    public BadRequestException(final ExceptionData exceptionData) {
        this.code = exceptionData.getCode();
        this.message = exceptionData.getMessage();
    }

}

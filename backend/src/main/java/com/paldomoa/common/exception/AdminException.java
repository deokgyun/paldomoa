package com.paldomoa.common.exception;

import lombok.Getter;

@Getter
public class AdminException extends RuntimeException {

    private final int code;
    private final String message;

    public AdminException(ExceptionData exceptionData) {
        this.code = exceptionData.getCode();
        this.message = exceptionData.getMessage();
    }
}

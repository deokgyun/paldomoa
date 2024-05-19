package com.paldomoa.common.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{

    private final ExceptionData exceptionData;

    public BusinessException(ExceptionData exceptionData) {
        super(exceptionData.getMessage());
        this.exceptionData = exceptionData;
    }
}

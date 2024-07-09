package com.paldomoa.common.error;

import com.paldomoa.common.exception.ApiException;
import com.paldomoa.common.exception.ExceptionData;

public record ApiExceptionEntity(
    String errorCode,
    String errorMessage
) {

    public ApiExceptionEntity(ApiException e) {
        this(
            e.getError().getCode(),
            e.getError().getMessage()
        );
    }

    public ApiExceptionEntity(RuntimeException e) {
        this(
            ExceptionData.RUNTIME_EXCEPTION.getCode(),
            e.getMessage()
        );
    }

    public ApiExceptionEntity(Exception e) {
        this(
            ExceptionData.INTERNAL_SERVER_EXCEPTION.getCode(),
            e.getMessage()
        );
    }
}

package com.paldomoa.common.api;

import com.paldomoa.common.error.ApiExceptionEntity;

public record ApiResult(
    String status,
    String message,
    ApiExceptionEntity exception
) {

    public ApiResult(String success, String message) {
        this(
            success,
            message,
            null
        );
    }
}

package com.paldomoa.common.exception;

import com.paldomoa.common.api.ApiResult;
import com.paldomoa.common.error.ApiExceptionEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionAdvice {

    private final GroupedOpenApi api;

    public ApiExceptionAdvice(GroupedOpenApi api) {
        this.api = api;
    }

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<ApiResult> exceptionHandler(HttpServletRequest request,
        final ApiException e) {

        ApiExceptionEntity apiExceptionEntity = new ApiExceptionEntity(e);

        return ResponseEntity.status(e.getError().getStatus())
            .body(new ApiResult("error", "", apiExceptionEntity));
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ApiResult> exceptionHandler(HttpServletRequest request,
        final RuntimeException e) {

        ApiExceptionEntity apiExceptionEntity = new ApiExceptionEntity(e);

        e.printStackTrace();

        return ResponseEntity
            .status(ExceptionData.RUNTIME_EXCEPTION.getStatus())
            .body(new ApiResult("error", "", apiExceptionEntity));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiResult> exceptionHandler(HttpServletRequest request, Exception e) {

        ApiExceptionEntity apiExceptionEntity = new ApiExceptionEntity(e);

        return ResponseEntity
            .status(ExceptionData.INTERNAL_SERVER_EXCEPTION.getStatus())
            .body(new ApiResult("error", "", apiExceptionEntity));
    }
}

package com.paldomoa.common.exception;


import static com.paldomoa.common.exception.ExceptionData.INTERNAL_SEVER_ERROR;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class ApiExceptionAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        final MethodArgumentNotValidException e,
        final HttpHeaders headers,
        final HttpStatusCode status,
        final WebRequest request
    ) {
        log.warn(e.getMessage(), e);

        final String errMessage = Objects.requireNonNull(e.getBindingResult().getFieldError())
            .getDefaultMessage();

        return ResponseEntity.badRequest()
            .body(new ExceptionResponse(ExceptionData.INVALID_REQUEST.getCode(), errMessage));
    }


    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ExceptionResponse> handleAuthException(
        final AuthException e) {

        log.warn(e.getMessage(), e);

        return ResponseEntity.badRequest()
            .body(new ExceptionResponse(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(AdminException.class)
    public ResponseEntity<ExceptionResponse> handleAdminException(final AdminException e) {

        log.warn(e.getMessage(), e);

        return ResponseEntity.badRequest()
            .body(new ExceptionResponse(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponse> handleBadRequestException(
        final BadRequestException e) {

        log.warn(e.getMessage(), e);

        return ResponseEntity.badRequest()
            .body(new ExceptionResponse(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ExceptionResponse> handleException(
        final BadRequestException e) {

        log.warn(e.getMessage(), e);

        return ResponseEntity.badRequest()
            .body(new ExceptionResponse(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(final Exception e) {

        log.error(e.getMessage(), e);

        return ResponseEntity.internalServerError()
            .body(new ExceptionResponse(INTERNAL_SEVER_ERROR.getCode(),
                INTERNAL_SEVER_ERROR.getMessage()));
    }

}

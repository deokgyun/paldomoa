package com.paldomoa.common.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ExceptionData {

    // System Exception
    RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "E0001"),
    ACCESS_DENIED_EXCEPTION(HttpStatus.UNAUTHORIZED, "E0002"),
    INTERNAL_SERVER_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "E0003"),
    // Custom Exception
    SECURITY_EXCEPTION(HttpStatus.UNAUTHORIZED, "CE0001"),
    ;

    private final HttpStatus status;
    private final String code;
    private String message;

    ExceptionData(HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }

    ExceptionData(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}

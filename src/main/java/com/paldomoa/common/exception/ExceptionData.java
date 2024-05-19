package com.paldomoa.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionData {

    AUTHORIZATION_SERVER_ERROR(9001,"인가 관련 서버 내부의 오류입니다.",500);

    private final int code;
    private final String message;
    private final int statusCode;

}

package com.test.fasoo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CustomErrorCode {

    UNDEFINED_AUTH_TYPE(HttpStatus.BAD_REQUEST, "INVALID_AUTH_TYPE", "존재하지 않는 권한 유형입니다."),
    DUPLICATED_AUTH(HttpStatus.BAD_REQUEST, "DUPLICATED_AUTH", "이미 권한이 존재합니다."),
    BEGIN_AFTER_EXPIRE(HttpStatus.BAD_REQUEST, "BEGIN_AFTER_EXPIRE", "권한 시작일이 만료일 이후 입니다.")
    ;


    CustomErrorCode(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    private HttpStatus httpStatus;
    private String code;
    private String message;

}

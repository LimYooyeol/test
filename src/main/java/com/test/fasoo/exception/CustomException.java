package com.test.fasoo.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException{

    private HttpStatus httpStatus;

    private String code;

    private String message;

    public CustomException(CustomErrorCode errorCode){
        this.httpStatus = errorCode.getHttpStatus();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
}

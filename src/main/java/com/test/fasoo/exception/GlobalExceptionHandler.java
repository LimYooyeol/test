package com.test.fasoo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e){

        BindingResult bindingResult = e.getBindingResult();
        StringBuilder builder = new StringBuilder();
        for(FieldError fieldError : bindingResult.getFieldErrors()){
            builder.append("[");
            builder.append(fieldError.getField());
            builder.append("](은)는 ");
            builder.append(fieldError.getDefaultMessage());
            builder.append(" 입력된 값: [");
            builder.append(fieldError.getRejectedValue());
            builder.append("]");
        }

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode("INVALID_PARAMETER");
        errorResponse.setMessage(builder.toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(DateTimeParseException.class)
    protected ResponseEntity handleDateTimeParseException(DateTimeParseException e){
        return handleCustomException(new CustomException(CustomErrorCode.INVALID_DATE_FORMAT));
    }


    @ExceptionHandler(CustomException.class)
    protected ResponseEntity handleCustomException(CustomException e){
        return ResponseEntity.status(e.getHttpStatus()).body(new ErrorResponse(e.getCode(), e.getMessage()));
    }

}

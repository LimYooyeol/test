package com.test.fasoo.exception;

import lombok.Data;

@Data
public class ErrorResponse {
    private String code;

    private String message;
}

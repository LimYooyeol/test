package com.test.fasoo.controller;

import com.test.fasoo.dto.AuthUser.AuthUserRequest;
import com.test.fasoo.dto.AuthUser.AuthUserResponse;
import com.test.fasoo.exception.CustomException;
import com.test.fasoo.exception.ErrorResponse;
import com.test.fasoo.service.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequiredArgsConstructor
public class AuthUserController {
    private final AuthUserService authUserService;

    @PostMapping("/data-auth")
    public AuthUserResponse authUserAdd(@RequestBody @Valid AuthUserRequest authUserRequest){
        AuthUserResponse authUserResponse = authUserService.addAuthUser(authUserRequest);


        return authUserResponse;
    }


}

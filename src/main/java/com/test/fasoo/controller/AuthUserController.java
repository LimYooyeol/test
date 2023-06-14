package com.test.fasoo.controller;

import com.test.fasoo.dto.AuthUser.AuthUserRequest;
import com.test.fasoo.dto.AuthUser.AuthUserResponse;
import com.test.fasoo.service.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthUserController {
    private final AuthUserService authUserService;

    @PostMapping("/data-auth")
    public AuthUserResponse authUserAdd(@RequestBody @Valid AuthUserRequest authUserRequest){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        AuthUserResponse authUserResponse = authUserService.addAuthUser(authUserRequest);


        return authUserResponse;
    }


}

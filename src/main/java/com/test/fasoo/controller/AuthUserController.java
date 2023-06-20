package com.test.fasoo.controller;

import com.test.fasoo.dto.AuthUser.AuthId;
import com.test.fasoo.dto.AuthUser.AuthIdList;
import com.test.fasoo.dto.AuthUser.AuthUserRequest;
import com.test.fasoo.dto.AuthUser.AuthUserResponse;
import com.test.fasoo.service.AuthUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthUserController {
    private final AuthUserService authUserService;

    /*
        권한 추가
     */
    @PostMapping("/auth")
    public AuthIdList authUserAdd(@RequestBody @Valid AuthUserRequest authUserRequest){
        List<AuthId> authIds = authUserService.addAuthUser(authUserRequest);


        return new AuthIdList(authIds);
    }


}

package com.test.fasoo.controller;


import com.test.fasoo.dto.UserAuth;
import com.test.fasoo.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userAuth")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserAuthService userAuthService;

    @PostMapping
    public int createUserAuth(@RequestBody UserAuth userAuth){
        System.out.println(userAuth);
        return userAuthService.createUserAuth(userAuth);
    }

}

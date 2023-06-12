package com.test.fasoo.controller;


import com.test.fasoo.dto.UserAuth;
import com.test.fasoo.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userAuth")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserAuthService userAuthService;

    //유저 권한 생성
    @PostMapping("")
    public int createUserAuth(@RequestBody UserAuth userAuth){
        System.out.println(userAuth);
        return userAuthService.createUserAuth(userAuth);
    }

    //유저 권한 조회

    @GetMapping("")
    public UserAuth readUserAuth(@RequestParam long userId){
        UserAuth resUserAuth;

        resUserAuth = userAuthService.readUserAuth(userId);

        System.out.println(resUserAuth);

        return resUserAuth;
    }



}

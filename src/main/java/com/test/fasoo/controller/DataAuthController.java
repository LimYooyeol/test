package com.test.fasoo.controller;


import com.test.fasoo.dto.AuthUser;
import com.test.fasoo.service.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/data-auth")
@RequiredArgsConstructor
public class DataAuthController {

    private final AuthUserService authUserService;

    //유저 권한 생성
    @PostMapping("")
    public int createAuthUser(@RequestBody AuthUser authUser){
//        System.out.println(authUser);
        return authUserService.createUserAuth(authUser);
    }

    //유저 권한 조회
    @GetMapping("")
    public AuthUser readAuthUser(@RequestParam String userId){
        AuthUser resAuthUser;

        resAuthUser = authUserService.readUserAuth(userId);

//        System.out.println(resAuthUser);

        return resAuthUser;
    }

    @GetMapping("/list")
    public List<AuthUser> getUserList(@RequestParam String userId){
        List<AuthUser> userList;

        userList = authUserService.getUserList(userId);

        return userList;
    }

    @GetMapping("/list/me")
    public List<AuthUser> getAuthList(@RequestParam String userId){
        List<AuthUser> authList;

        authList = authUserService.getAuthList(userId);

        return authList;
    }
}

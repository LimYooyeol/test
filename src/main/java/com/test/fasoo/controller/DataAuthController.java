package com.test.fasoo.controller;


import com.test.fasoo.dto.AuthUser;
import com.test.fasoo.service.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authUser")
@RequiredArgsConstructor
public class AuthUserController {

    private final AuthUserService userAuthService;

    //유저 권한 생성
    @PostMapping("")
    public int createAuthUser(@RequestBody AuthUser authUser){
        System.out.println(authUser);
        return userAuthService.createUserAuth(authUser);
    }

    //유저 권한 조회

    @GetMapping("")
    public AuthUser readAuthUser(@RequestParam long userId){
        AuthUser resAuthUser;

        resAuthUser = userAuthService.readUserAuth(userId);

        System.out.println(resAuthUser);

        return resAuthUser;
    }
}

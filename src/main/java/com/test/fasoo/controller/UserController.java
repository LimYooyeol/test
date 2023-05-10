package com.test.fasoo.controller;


import com.test.fasoo.dto.User;
import com.test.fasoo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @PostMapping("/")
    public String createUser(User user){
        System.out.println(user);
        userService.createUser(user);
        return "200";
    }

}

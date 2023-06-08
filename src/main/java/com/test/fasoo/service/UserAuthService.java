package com.test.fasoo.service;

import com.test.fasoo.dto.UserAuth;
import com.test.fasoo.mapper.UserAuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService {
    @Autowired
    private UserAuthMapper userAuthMapper;

    public int createUserAuth(UserAuth userAuth){
        return userAuthMapper.createUserAuth(userAuth);
    }

}

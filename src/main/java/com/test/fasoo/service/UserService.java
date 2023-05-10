package com.test.fasoo.service;

import com.test.fasoo.dto.User;
import com.test.fasoo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;


    public int createUser(User user) {

        return userMapper.createUser(user);
    }
}

package com.test.fasoo.mapper;

import com.test.fasoo.dto.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public int createUser(User user);
}

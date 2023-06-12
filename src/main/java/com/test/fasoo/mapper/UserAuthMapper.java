package com.test.fasoo.mapper;

import com.test.fasoo.dto.UserAuth;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAuthMapper {
    public int createUserAuth(UserAuth userAuth);
    public UserAuth readUserAuth(long userId);
}

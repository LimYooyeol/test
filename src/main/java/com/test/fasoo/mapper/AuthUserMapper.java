package com.test.fasoo.mapper;


import com.test.fasoo.dto.AuthUser.AuthUserRequest;
import com.test.fasoo.dto.AuthUser.AuthUserResponse;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AuthUserMapper {

    public int insertAuthUser(AuthUserRequest authUserRequest);

    public AuthUserResponse selectAuthUserResponseByRequestId(String requestId);

}

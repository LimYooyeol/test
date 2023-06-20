package com.test.fasoo.mapper;


import com.test.fasoo.dto.AuthUser.AuthUserRequest;
import com.test.fasoo.dto.AuthUser.AuthUserResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface AuthUserMapper {

    public int insertAuthUser(AuthUserRequest authUserRequest);

    public List<AuthUserResponse> selectAuthUserResponseByRequestId(String requestId);

}

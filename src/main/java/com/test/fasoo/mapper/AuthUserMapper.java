package com.test.fasoo.mapper;

import com.test.fasoo.dto.AuthUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthUserMapper {
    public int createAuthUser(AuthUser authUser);
    public AuthUser readAuthUser(String userId);

    public List<AuthUser> getUserList(String userId);

    public List<AuthUser> getAuthList(String userId);

}

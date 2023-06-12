package com.test.fasoo.service;

import com.test.fasoo.dto.AuthUser;
import com.test.fasoo.mapper.AuthUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthUserService {
    @Autowired
    private AuthUserMapper authUserMapper;

    //유저 권한 추가
    public int createUserAuth(AuthUser authUser){
        return authUserMapper.createAuthUser(authUser);
    }
    //유저 권한 조회
    public AuthUser readUserAuth(String userId){
        return authUserMapper.readAuthUser(userId);
    }
    //권한 목록 조회(관리자)
    public List<AuthUser> getUserList(String userId){
        return authUserMapper.getUserList(userId);
    }
    //권한 목록 조회(유저)
    public List<AuthUser> getAuthList(String userId){
        return authUserMapper.getAuthList(userId);
    }
}

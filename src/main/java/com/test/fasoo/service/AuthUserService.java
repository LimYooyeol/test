package com.test.fasoo.service;

import com.test.fasoo.dto.AuthUser.AuthUserRequest;
import com.test.fasoo.dto.AuthUser.AuthUserResponse;
import com.test.fasoo.exception.CustomErrorCode;
import com.test.fasoo.exception.CustomException;
import com.test.fasoo.mapper.AuthUserMapper;
import com.test.fasoo.vo.AuthUserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

import static com.test.fasoo.exception.CustomErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthUserService {

    private final AuthUserMapper authUserMapper;


    @Transactional(readOnly = false)
    public AuthUserResponse addAuthUser(AuthUserRequest authUserRequest){
        if(authUserRequest.getBeginDate().isAfter(authUserRequest.getExpireDate())){
            throw new CustomException(BEGIN_AFTER_EXPIRE);
        }

        try{
            authUserMapper.insertAuthUser(authUserRequest);
        }catch(DataIntegrityViolationException e){
            SQLException cause = (SQLException) e.getCause();

            if(cause.getErrorCode() == 1048){   // DATA_ID가 없어 FK가 null이 되는 경우
                throw new CustomException(UNDEFINED_AUTH_TYPE);
            }else if(cause.getErrorCode() == 1062){     // UNIQUE 조건 위반
                throw new CustomException(DUPLICATED_AUTH);
            }
        }

        AuthUserResponse authUserResponse = authUserMapper.selectAuthUserResponseByRequestId(authUserRequest.getRequestId());

        return authUserResponse;
    }

}

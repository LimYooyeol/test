package com.test.fasoo.service;

import com.test.fasoo.dto.AuthUser.AuthId;
import com.test.fasoo.dto.AuthUser.AuthUserRequest;
import com.test.fasoo.dto.AuthUser.AuthUserResponse;
import com.test.fasoo.exception.CustomException;
import com.test.fasoo.mapper.AuthUserMapper;
import io.jsonwebtoken.lang.Assert;
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

    /*
        권한 추가
     */
    @Transactional(readOnly = false)
    public List<AuthId> addAuthUser(AuthUserRequest authUserRequest){
        if(authUserRequest.getBeginDate().isAfter(authUserRequest.getExpireDate())){
            throw new CustomException(BEGIN_AFTER_EXPIRE);
        }
        if(authUserMapper.countAuthUserByRequestId(authUserRequest.getRequestId()) > 0){
            throw new CustomException(DUPLICATED_REQUEST_ID);
        }

        try{
            authUserMapper.insertAuthUser(authUserRequest);
        }catch(DataIntegrityViolationException e){
            SQLException cause = (SQLException) e.getCause();

            if(cause.getErrorCode() == 1062){     // UNIQUE 조건 위반
                throw new CustomException(DUPLICATED_AUTH);
            }else if(cause.getErrorCode() == 1452){     // FK 조건 위반
                throw new CustomException(UNDEFINED_AUTH_TYPE);
            } else{
                throw new RuntimeException("UNEXPECTED_ERROR_EXECUTING_QUERY");
            }
        }catch (Exception e){
            throw new RuntimeException("UNEXPECTED_ERROR_EXECUTING_QUERY");
        }

        return authUserMapper.selectAuthIdsByRequestId(authUserRequest.getRequestId());
    }

}

package com.test.fasoo.service;

import com.test.fasoo.dto.AuthUser.AuthUserRequest;
import com.test.fasoo.dto.AuthUser.AuthUserResponse;
import com.test.fasoo.exception.CustomErrorCode;
import com.test.fasoo.exception.CustomException;
import com.test.fasoo.mapper.AuthUserMapper;
import com.test.fasoo.vo.AuthUserVo;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
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

        List<AuthUserResponse> authUserResponse = authUserMapper.selectAuthUserResponseByRequestId(authUserRequest.getRequestId());

        // REQUEST_ID가 중복되는 경우
        // DISTINCT REQUEST_ID, USER_ID, AUTH_TYPE_NAME, BEGIN_DATE, EXPIRE_DATE, CREATE_DATE, UPDATE_DATE 가 여러 개라서 select 결과가 여러 개 발생
        // or
        // 2개의 요청이 거의 동시에 들어오는 경우, DISTINCT 는 하나지만, 읽어지는 데이터는 더 많음 (서로 다른 요청으로 들어온 것을 구분할 수 없음)
        if((authUserResponse.size() != 1) || authUserResponse.get(0).getDataIdList().size() != authUserRequest.getDataIdList().size()){
            throw new CustomException(DUPLICATED_REQUEST_ID);
        }

        return authUserResponse.get(0);
    }

}

package com.test.fasoo.mapper;

import com.test.fasoo.dto.AuthUser.AuthId;
import com.test.fasoo.dto.AuthUser.AuthUserRequest;
import com.test.fasoo.dto.AuthUser.AuthUserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AuthUserMapperTest {

    @Autowired
    private AuthUserMapper authUserMapper;

    @Test
    public void 권한_카운트_BY_REQUEST_ID_테스트1(){
        // given

        // when
        int count = authUserMapper.countAuthUserByRequestId("REQUEST1");

        // then
        assertEquals(0, count);
    }

    @Test
    public void 권한추가_삽입_테스트(){

        // given
        AuthUserRequest authUserRequest = new AuthUserRequest();
        authUserRequest.setRequestId("REQUEST_0001");
        authUserRequest.setAuthTypeId("DATA_USE");
        authUserRequest.setUserId("USER1");

        String[] dataIdArray = {"DATA01", "DATA02", "DATA03"};
        authUserRequest.setResourceIdList(Arrays.asList(dataIdArray));

        authUserRequest.setBeginDate(LocalDate.of(2023, 7, 1));
        authUserRequest.setExpireDate(LocalDate.of(2023, 8, 1));

        // when
        int changedRows = authUserMapper.insertAuthUser(authUserRequest);

        // then
        assertEquals(dataIdArray.length, changedRows);
    }

    @Test
    public void 권한_카운트_BY_REQUEST_ID_테스트2(){
        // given
        String requestId = "REQUEST_1";
        String[] dataIdArray = {"DATA01", "DATA02", "DATA03"};
        AuthUserRequest authUserRequest = new AuthUserRequest(
                requestId,
                "DATA_USE",
                "USER1",
                Arrays.asList(dataIdArray),
                LocalDate.of(2023, 7, 1),
                LocalDate.of(2023, 8, 1)
        );
        authUserMapper.insertAuthUser(authUserRequest);

        // when
        int count = authUserMapper.countAuthUserByRequestId(requestId);

        // then
        assertEquals(dataIdArray.length, count);
    }

    @Test
    public void 권한추가_조회_테스트(){
        // given
        AuthUserRequest authUserRequest = new AuthUserRequest();
        authUserRequest.setRequestId("REQUEST_0001");
        authUserRequest.setAuthTypeId("DATA_USE");
        authUserRequest.setUserId("USER1");

        String[] dataIdArray = {"DATA01", "DATA02", "DATA03"};
        authUserRequest.setResourceIdList(Arrays.asList(dataIdArray));

        authUserRequest.setBeginDate(LocalDate.of(2023, 7, 1));
        authUserRequest.setExpireDate(LocalDate.of(2023, 8, 1));

        authUserMapper.insertAuthUser(authUserRequest);

        // when
        List<AuthId> authIds = authUserMapper.selectAuthIdsByRequestId(authUserRequest.getRequestId());

        // then
        assertEquals(dataIdArray.length, authIds.size());
    }
}
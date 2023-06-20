package com.test.fasoo.mapper;

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
    public void 권한추가_삽입_테스트(){

        // given
        AuthUserRequest authUserRequest = new AuthUserRequest();
        authUserRequest.setRequestId("REQUEST_0001");
        authUserRequest.setAuthTypeName("DATA_USE");
        authUserRequest.setUserId("USER1");

        String[] dataIdArray = {"DATA01", "DATA02", "DATA03"};
        authUserRequest.setDataIdList(Arrays.asList(dataIdArray));

        authUserRequest.setBeginDate(LocalDate.of(2023, 7, 1));
        authUserRequest.setExpireDate(LocalDate.of(2023, 8, 1));

        // when
        int changedRows = authUserMapper.insertAuthUser(authUserRequest);

        // then
        assertEquals(dataIdArray.length, changedRows);
    }

    @Test
    public void 권한추가_조회_테스트(){
        // given
        String[] dataIdArray1 = {"DATA01", "DATA02", "DATA03"};
        AuthUserRequest authUserRequest1 = new AuthUserRequest(
                "REQUEST_1",
                "DATA_USE",
                "USER1",
                Arrays.asList(dataIdArray1),
                LocalDate.of(2023, 7, 1),
                LocalDate.of(2023, 8, 1)
        );

        String[] dataIdArray2= {"DATA04", "DATA05"};
        AuthUserRequest authUserRequest2 = new AuthUserRequest(
                "REQUEST_2",
                "DATA_USE",
                "USER1",
                Arrays.asList(dataIdArray2),
                LocalDate.of(2023, 7, 2),
                LocalDate.of(2023, 8, 2)
        );

        authUserMapper.insertAuthUser(authUserRequest1);
        authUserMapper.insertAuthUser(authUserRequest2);

        // when
        List<AuthUserResponse> authUserResponse2 = authUserMapper.selectAuthUserResponseByRequestId(authUserRequest2.getRequestId());

        // then
        assertNotNull(authUserResponse2);
        assertEquals(1, authUserResponse2.size());
        assertEquals(dataIdArray2.length, authUserResponse2.get(0).getDataIdList().size());

        System.out.println(authUserResponse2);
    }
}
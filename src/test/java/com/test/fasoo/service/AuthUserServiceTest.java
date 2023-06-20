package com.test.fasoo.service;

import com.test.fasoo.dto.AuthUser.AuthUserRequest;
import com.test.fasoo.dto.AuthUser.AuthUserResponse;
import com.test.fasoo.exception.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.Local;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static com.test.fasoo.exception.CustomErrorCode.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AuthUserServiceTest {

    @Autowired
    private AuthUserService authUserService;

    @Test
    public void 권한추가_성공_테스트(){
        // given
        String[] dataIdArray = {"DATA1", "DATA2", "DATA3"};
        AuthUserRequest authUserRequest = new AuthUserRequest(
                "REQUEST_1",
                "DATA_USE",
                "USER1",
                Arrays.asList(dataIdArray),
                LocalDate.of(2023, 7, 1),
                LocalDate.of(2023, 8, 1)
        );

        // when
        AuthUserResponse authUserResponse = authUserService.addAuthUser(authUserRequest);

        // then
        assertNotNull(authUserResponse);
        assertEquals(dataIdArray.length, authUserResponse.getDataIdList().size());
    }

    @Test
    @DisplayName("존재하지 않는 권한 유형을 추가하는 경우")
    public void 권한추가_실패_테스트1(){
        // given
        String[] dataIdArray = {"DATA1", "DATA2", "DATA3"};
        AuthUserRequest authUserRequest = new AuthUserRequest(
                "REQUEST_1",
                "UNDEFINED_AUTH_TYPE",
                "USER1",
                Arrays.asList(dataIdArray),
                LocalDate.of(2023, 7, 1),
                LocalDate.of(2023, 8, 1)
        );

        // when
        CustomException customException = assertThrows(CustomException.class,
                () -> authUserService.addAuthUser(authUserRequest));

        // then
        assertEquals(UNDEFINED_AUTH_TYPE.getCode(), customException.getCode());
    }

    @Test
    @DisplayName("이미 존재하는 권한을 추가하는 경우")
    public void 권한추가_실패_테스트2(){
        // given
        String[] dataIdArray1 = {"DATA1", "DATA2", "DATA3"};
        AuthUserRequest authUserRequest1 = new AuthUserRequest(
                "REQUEST_1",
                "DATA_USE",
                "USER1",
                Arrays.asList(dataIdArray1),
                LocalDate.of(2023, 7, 1),
                LocalDate.of(2023, 8, 1)
        );

        String[] dataIdArray2 = {"DATA3", "DATA4"}; // 데이터 중복
        AuthUserRequest authUserRequest2 = new AuthUserRequest(
                "REQUEST_2",
                "DATA_USE",
                "USER1",
                Arrays.asList(dataIdArray2),
                LocalDate.of(2023, 7, 1),
                LocalDate.of(2023, 8, 1)
        );

        authUserService.addAuthUser(authUserRequest1);

        // when
        CustomException customException = assertThrows(CustomException.class,
                () -> authUserService.addAuthUser(authUserRequest2));


        // then
        assertEquals(DUPLICATED_AUTH.getCode(), customException.getCode());
    }

    @Test
    @DisplayName("권한 시작일이 만료일 이후인 경우")
    public void 권한추가_실패_테스트3(){
        // given
        String[] dataIdArray = {"DATA1", "DATA2", "DATA3"};
        AuthUserRequest authUserRequest = new AuthUserRequest(
                "REQUEST_1",
                "UNDEFINED_AUTH_TYPE",
                "USER1",
                Arrays.asList(dataIdArray),
                LocalDate.of(2023, 9, 1),
                LocalDate.of(2023, 8, 1)
        );

        // when
        CustomException customException = assertThrows(CustomException.class, () -> authUserService.addAuthUser(authUserRequest));

        // then
        assertEquals(BEGIN_AFTER_EXPIRE.getCode(), customException.getCode());
    }

    @Test
    @DisplayName("REQUEST_ID가 중복되는 경우 - 다른 형태의 요청이 REQUEST_ID가 같은 경우")
    public void 권한추가_실패_테스트4(){
        // given
        String[] dataIdArray1 = {"DATA1", "DATA2", "DATA3"};
        AuthUserRequest authUserRequest1 = new AuthUserRequest(
                "REQUEST_1",
                "DATA_USE",
                "USER1",
                Arrays.asList(dataIdArray1),
                LocalDate.of(2023, 7, 1),
                LocalDate.of(2023, 8, 1)
        );

        String[] dataIdArray2 = {"DATA4"};
        AuthUserRequest authUserRequest2 = new AuthUserRequest(
                "REQUEST_1",
                "DATA_USE",
                "USER2",
                Arrays.asList(dataIdArray2),
                LocalDate.of(2023, 8, 1),
                LocalDate.of(2023, 8, 14)
        );

        AuthUserResponse authUserResponse1 = authUserService.addAuthUser(authUserRequest1);

        // when
        CustomException customException = assertThrows(CustomException.class, () -> authUserService.addAuthUser(authUserRequest2));

        // then
        assertEquals(DUPLICATED_REQUEST_ID.getCode(), customException.getCode());
    }

    @Test
    @DisplayName("REQUEST_ID가 중복되는 경우 - 동시에 동일한 API가 호출되는 경우")
    public void 권한추가_실패_테스트5(){
        // given
        String[] dataIdArray1 = {"DATA1", "DATA2", "DATA3"};
        AuthUserRequest authUserRequest1 = new AuthUserRequest(
                "REQUEST_1",
                "DATA_USE",
                "USER1",
                Arrays.asList(dataIdArray1),
                LocalDate.of(2023, 7, 1),
                LocalDate.of(2023, 8, 1)
        );

        String[] dataIdArray2 = {"DATA4"};
        AuthUserRequest authUserRequest2 = new AuthUserRequest(
                "REQUEST_1",
                "DATA_USE",
                "USER1",
                Arrays.asList(dataIdArray2),
                LocalDate.of(2023, 7, 1),
                LocalDate.of(2023, 8, 1)
        );

        AuthUserResponse authUserResponse1 = authUserService.addAuthUser(authUserRequest1);

        // when
        CustomException customException = assertThrows(CustomException.class, () -> authUserService.addAuthUser(authUserRequest2));

        // then
        assertEquals(DUPLICATED_REQUEST_ID.getCode(), customException.getCode());

    }

}
package com.test.fasoo.service;

import com.test.fasoo.dto.UserRole.UserRoleRequest;
import com.test.fasoo.dto.UserRole.UserRoleResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRoleServiceTest {
    @Autowired
    private UserRoleService userRoleService;

    @Test
    public void 관리자_추가_성공_테스트(){
        // given
        UserRoleRequest userRoleRequest = new UserRoleRequest();
        userRoleRequest.setUserId("USER1");
        userRoleRequest.setAdminTypeId("SYSTEM_ADMIN");

        // when
        UserRoleResponse userRoleResponse = userRoleService.addUserRole(userRoleRequest);

        // then
        assertNotNull(userRoleResponse);
        assertNotNull(userRoleResponse.getUserId());
        System.out.println(userRoleResponse);
    }

}
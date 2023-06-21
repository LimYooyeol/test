package com.test.fasoo.mapper;

import com.test.fasoo.dto.AdminTypeAuth.AdminTypeAuthCheck;
import com.test.fasoo.dto.AdminTypeAuth.AdminTypeAuthSearch;
import com.test.fasoo.exception.CustomErrorCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AdminTypeAuthMapperTest {
    @Autowired
    private AdminTypeAuthMapper adminTypeAuthMapper;

    @Test
    public void 권한조회_테스트(){
        // given

        // when
        AdminTypeAuthCheck adminTypeAuthCheck = adminTypeAuthMapper.checkAdminTypeAuthByAuthTypeIdAndAdminTypeId(new AdminTypeAuthSearch("DATA_USE", "SYSTEM_ADMIN"));


        // then
        assertNotNull(adminTypeAuthCheck);
        System.out.println(adminTypeAuthCheck);

    }

}
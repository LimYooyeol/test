package com.test.fasoo.mapper;

import com.test.fasoo.dto.AdminTypeAuth.AdminTypeAuthCheckResult;
import com.test.fasoo.dto.AdminTypeAuth.AdminTypeAuthSearch;
import com.test.fasoo.dto.AuthCheckResult;
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
        AdminTypeAuthCheckResult adminTypeAuthCheckResult = adminTypeAuthMapper.checkAdminTypeAuthByAuthTypeIdAndAdminTypeId(new AdminTypeAuthSearch("DATA_USE", "SYSTEM_ADMIN"));


        // then
        assertNotNull(adminTypeAuthCheckResult);
        assertNotNull(adminTypeAuthCheckResult.getAuthCheckResult());
        assertEquals(AuthCheckResult.AUTHORIZED, adminTypeAuthCheckResult.getAuthCheckResult());
    }

}
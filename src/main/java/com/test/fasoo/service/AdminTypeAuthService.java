package com.test.fasoo.service;

import com.test.fasoo.dto.AdminTypeAuth.AdminTypeAuthCheck;
import com.test.fasoo.dto.AdminTypeAuth.AdminTypeAuthSearch;
import com.test.fasoo.mapper.AdminTypeAuthMapper;
import com.test.fasoo.vo.AdminTypeAuthVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminTypeAuthService {
    private final AdminTypeAuthMapper adminTypeAuthMapper;

    public AdminTypeAuthCheck checkAdminTypeAuth(AdminTypeAuthSearch adminTypeAuthSearch){
        AdminTypeAuthCheck adminTypeAuthCheck = adminTypeAuthMapper.checkAdminTypeAuthByAuthTypeIdAndAdminTypeId(adminTypeAuthSearch);

        return adminTypeAuthCheck;
    }
}

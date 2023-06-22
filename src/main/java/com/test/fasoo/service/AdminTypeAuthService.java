package com.test.fasoo.service;

import com.test.fasoo.dto.AdminTypeAuth.AdminTypeAuthCheckResult;
import com.test.fasoo.dto.AdminTypeAuth.AdminTypeAuthSearch;
import com.test.fasoo.mapper.AdminTypeAuthMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminTypeAuthService {
    private final AdminTypeAuthMapper adminTypeAuthMapper;

    public AdminTypeAuthCheckResult checkAdminTypeAuth(AdminTypeAuthSearch adminTypeAuthSearch){
        AdminTypeAuthCheckResult adminTypeAuthCheckResult = adminTypeAuthMapper.checkAdminTypeAuthByAuthTypeIdAndAdminTypeId(adminTypeAuthSearch);

        return adminTypeAuthCheckResult;
    }
}

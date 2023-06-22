package com.test.fasoo.mapper;

import com.test.fasoo.dto.AdminTypeAuth.AdminTypeAuthCheckResult;
import com.test.fasoo.dto.AdminTypeAuth.AdminTypeAuthSearch;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminTypeAuthMapper {

    public AdminTypeAuthCheckResult checkAdminTypeAuthByAuthTypeIdAndAdminTypeId(AdminTypeAuthSearch adminTypeAuthSearch);
}

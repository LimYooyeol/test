package com.test.fasoo.mapper;

import com.test.fasoo.dto.AdminTypeAuth.AdminTypeAuthCheck;
import com.test.fasoo.dto.AdminTypeAuth.AdminTypeAuthSearch;
import com.test.fasoo.vo.AdminTypeAuthVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminTypeAuthMapper {

    public AdminTypeAuthCheck checkAdminTypeAuthByAuthTypeIdAndAdminTypeId(AdminTypeAuthSearch adminTypeAuthSearch);
}

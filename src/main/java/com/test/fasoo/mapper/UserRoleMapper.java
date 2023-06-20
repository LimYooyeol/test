package com.test.fasoo.mapper;


import com.test.fasoo.dto.UserRole.UserRoleRequest;
import com.test.fasoo.vo.UserRoleVo;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserRoleMapper {

    // 관리자 추가
    public int insertUserRole(UserRoleRequest userRoleRequest);

    // 관리자 조회
    public UserRoleVo selectUserRoleByUserId(String userId);

}

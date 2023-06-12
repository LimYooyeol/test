package com.test.fasoo.service;

import com.test.fasoo.dto.UserRole.UserRoleRequest;
import com.test.fasoo.dto.UserRole.UserRoleResponse;
import com.test.fasoo.mapper.UserRoleMapper;
import com.test.fasoo.vo.UserRoleVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserRoleService {
    
    private final UserRoleMapper userRoleMapper;

    @Transactional(readOnly = false)
    public UserRoleResponse addUserRole(UserRoleRequest userRoleRequest){
        int result = userRoleMapper.insertUserRole(userRoleRequest);
        UserRoleVo userRoleVO = userRoleMapper.selectUserRoleById(userRoleRequest.getId());

        return new UserRoleResponse(userRoleVO);
    }

}

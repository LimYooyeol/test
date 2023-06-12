package com.test.fasoo.dto.UserRole;

import com.test.fasoo.vo.UserRoleVO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserRoleResponse {

    private String userId;

    private String adminTypeId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public UserRoleResponse(){}

    public UserRoleResponse(UserRoleVO userRoleVO) {
        this.userId = userRoleVO.getUserId();
        this.adminTypeId = userRoleVO.getAdminTypeId();
        this.createTime = userRoleVO.getCreateTime();
        this.updateTime = userRoleVO.getUpdateTime();
    }
}

package com.test.fasoo.dto.UserRole;

import com.test.fasoo.vo.UserRoleVo;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserRoleResponse {

    private String userId;

    private String adminTypeId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public UserRoleResponse(){}

    public UserRoleResponse(UserRoleVo userRoleVO) {
        this.userId = userRoleVO.getUserId();
        this.adminTypeId = userRoleVO.getAdminTypeId();
        this.createTime = userRoleVO.getCreateTime();
        this.updateTime = userRoleVO.getUpdateTime();
    }
}

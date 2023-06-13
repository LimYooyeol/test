package com.test.fasoo.dto.UserRole;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserRoleRequest {

    private Integer id;

    @NotNull
    private String requestId;

    @NotNull
    private String userId;

    // SYSTEM_ADMIN, DATA_ADMIN, LIVINGLAB_ADMIN, HMMP_ADMIN
    @NotNull
    private String adminTypeId;
}

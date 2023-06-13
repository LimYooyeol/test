package com.test.fasoo.dto.AuthUser;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class AuthUserResponse {

    private String requestId;

    private String userId;

    private String authTypeName;

    private List<String> dataIdList;

    private LocalDate beginDate;

    private LocalDate expireDate;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

}


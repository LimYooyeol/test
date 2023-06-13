package com.test.fasoo.vo;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AuthUserVo {
    private Long id;

    private String requestId;

    private String authTypeId;

    private String userId;

    private String dataId;

    private LocalDateTime beginDate;

    private LocalDateTime expireDate;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}

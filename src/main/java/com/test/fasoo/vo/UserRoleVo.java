package com.test.fasoo.vo;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserRoleVo {

    private Integer id;
    private String adminTypeId;
    private String userId;
    private LocalDateTime createTime;         // LocalDateTime 사용 괜찮을지
    private LocalDateTime updateTime;

}

package com.test.fasoo.dto;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class AuthUser {
    private long id;
    private String authTypeId;
    private String userId;
    private String resourceId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expireDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    public AuthUser(String authTypeId, String userId, String resourceId, Date beginDate, Date expireDate){
        this.authTypeId = authTypeId;
        this.userId = userId;
        this.resourceId = resourceId;
        this.beginDate = beginDate;
        this.expireDate = expireDate;
    }

    public AuthUser(String authTypeId, String userId, String resourceId, Date beginDate, Date expireDate, Date createTime, Date updateTime){
        this.authTypeId = authTypeId;
        this.userId = userId;
        this.resourceId = resourceId;
        this.beginDate = beginDate;
        this.expireDate = expireDate;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

}

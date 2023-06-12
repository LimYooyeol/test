package com.test.fasoo.dto;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAuth {
    private long authUserId;
    private long authTypeId;
    private long userId;
    private long resourceId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expireDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateDate;

    public UserAuth(long authTypeId, long userId, long resourceId, Date beginDate, Date expireDate){
        this.authTypeId = authTypeId;
        this.userId = userId;
        this.resourceId = resourceId;
        this.beginDate = beginDate;
        this.expireDate = expireDate;
    }
}

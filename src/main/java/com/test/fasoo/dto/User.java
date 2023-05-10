package com.test.fasoo.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//user정보 전송을 위한 DTO
@ToString
@Getter
@Setter
public class User {
    private int userId;
    private String userName;
    private String password;
    private String role;

    public User(){}
    public User(int userId, String userName, String password, String role){
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

}

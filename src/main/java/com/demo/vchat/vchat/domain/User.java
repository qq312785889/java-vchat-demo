package com.demo.vchat.vchat.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class User {
    private String userId;
    private String account;
    private String password;
    private String userName;
    private Integer userAge;
    private String userSex;
    private Integer status;
    //格式化时间  和数据库格式一样  select now()
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String userCreateTime;
}

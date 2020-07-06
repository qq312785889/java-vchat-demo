package com.demo.vchat.vchat.rule.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterDto {

    @NotBlank(message = "账号不能为空！")
    private String account;
    @NotBlank(message = "密码不能为空！")
    private String password;
    private String userName;
    private Integer userAge;
    private String userSex;
}

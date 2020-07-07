package com.demo.vchat.vchat.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    private String userId;

    @NotBlank(message = "账号不能为空！")
    private String account;
    @NotBlank(message = "密码不能为空！")
    private String password;
    private String userName;
    private Integer userAge;
    private String userSex;
}

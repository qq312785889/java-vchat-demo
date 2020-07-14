package com.demo.vchat.vchat.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDto {
    private String accessToken;
    @NotBlank(message = "appId不能为空")
    private String appId;
    @NotBlank(message = "appSecret不能为空")
    private String appSecret;

}

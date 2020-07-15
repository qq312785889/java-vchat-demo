package com.demo.vchat.vchat.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author 宁缺毋滥
 */
@Data
public class VchatDto {
    @NotBlank(message = "code为空！")
    private String code;
}

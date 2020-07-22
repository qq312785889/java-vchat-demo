package com.demo.vchat.vchat.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 宁缺毋滥
 */
@Data
public class User {
    private Integer id;
    private String openId;
    private String sessionKey;
    private String salt;
    private Integer roleId;
    private Integer locked;
    private String unionId;
    private Date lastLoginTime;
    private Integer errCode;
    private String errMsg;

}

package com.demo.vchat.vchat.domain;

import lombok.Data;

/**
 * @author 宁缺毋滥
 */
@Data
public class OpenId {

    private String openId;
    private String sessionKey;
    private String unionId;
    private Integer errCode;
    private String errMsg;


}

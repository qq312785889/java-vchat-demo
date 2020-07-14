package com.demo.vchat.vchat.domain;

import lombok.Data;

@Data
public class JSTicket {
    private String ticket;
    private Integer expiresIn;
    private Integer errCode;
    private String errMsg;
}

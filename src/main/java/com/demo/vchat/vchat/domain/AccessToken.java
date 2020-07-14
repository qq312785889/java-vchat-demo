package com.demo.vchat.vchat.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class AccessToken {
    private String accessToken;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expiresTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date saveTime;
    private Integer expiresIn;
    private String jsapiTicket;
}

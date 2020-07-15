package com.demo.vchat.vchat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AccessToken implements Serializable {
    private String accessToken;
    private Date expiresTime;
    private Date saveTime;
    private Integer expiresIn;
    private String jsapiTicket;

}

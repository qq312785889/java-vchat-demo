package com.demo.vchat.vchat.controller;

import com.demo.vchat.vchat.domain.HttpResultMassage;
import com.demo.vchat.vchat.util.HttpResultUtil;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/errorApi")
@Hidden
public class Controller {

    @GetMapping("/permissionError")
    public HttpResultMassage permissionError (){
        return HttpResultUtil.error(404,"您没有权限访问该资源");
    }

    @GetMapping("/toLogin")
    public HttpResultMassage notLogin (){
        return HttpResultUtil.error(404,"请登录后访问该资源");
    }

}

package com.demo.vchat.vchat.controller;

import com.demo.vchat.vchat.domain.HttpResultMassage;
import com.demo.vchat.vchat.util.HttpResultUtil;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 宁缺毋滥
 */
@RestController
@RequestMapping("/errorApi")
@Hidden
public class Controller extends BaseController {

    @GetMapping("/permissionError")
    public HttpResultMassage permissionError (){
        return HttpResultUtil.error(404,"您没有权限访问该资源");
    }

    @GetMapping("/toLogin")
    public HttpResultMassage notLogin (){
        return HttpResultUtil.error(404,"请登录后访问该资源");
    }

    @RequestMapping("/loginError")
    public HttpResultMassage loginError (){
        response.addHeader("errorCode","-1");
        return HttpResultUtil.error(-1,"token验证失败，请重新登录");
    }


}

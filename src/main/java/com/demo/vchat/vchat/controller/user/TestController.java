package com.demo.vchat.vchat.controller.user;

import com.demo.vchat.vchat.controller.BaseController;
import com.demo.vchat.vchat.domain.AccessToken;
import com.demo.vchat.vchat.domain.HttpResultMassage;
import com.demo.vchat.vchat.util.HttpResultUtil;
import com.demo.vchat.vchat.util.VchatUtil;
import io.swagger.v3.oas.annotations.Hidden;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/test")
public class TestController extends BaseController {

    @Hidden
    @RequestMapping("/toH5")
    public String toH5(){
        return "test/H5pages.html";
    }


    @GetMapping("/test")
    public HttpResultMassage<AccessToken> test() {
        AccessToken accessToken = VchatUtil.accessToken();
        System.out.println(accessToken);
        return HttpResultUtil.success(accessToken);
    }
    @GetMapping("/token")
    public HttpResultMassage token(){
        if (request.getHeader("errorMsg") != null){
            return HttpResultUtil.error(-1,request.getHeader("errorMsg"));
        }
        return HttpResultUtil.success();
    }
}

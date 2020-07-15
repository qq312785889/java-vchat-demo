package com.demo.vchat.vchat.controller.user;

import com.demo.vchat.vchat.domain.AccessToken;
import com.demo.vchat.vchat.domain.HttpResultMassage;
import com.demo.vchat.vchat.util.HttpResultUtil;
import com.demo.vchat.vchat.util.VchatUtil;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;

@Controller
public class TestController {

        @Hidden
        @RequestMapping("/toH5")
        public String toH5(){
            return "test/H5pages.html";
        }

        @Autowired
        private VchatUtil vchatUtil;

        @GetMapping("/test")
        public HttpResultMassage<AccessToken> test() {
                AccessToken accessToken = vchatUtil.accessToken();
                System.out.println(accessToken);
                return HttpResultUtil.success(accessToken);
        }
}

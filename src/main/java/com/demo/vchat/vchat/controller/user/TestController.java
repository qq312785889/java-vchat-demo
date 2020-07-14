package com.demo.vchat.vchat.controller.user;

import com.demo.vchat.vchat.util.VchatUtil;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

        @Hidden
        @RequestMapping("/toH5")
        public String toH5(){
            return "test/H5pages.html";
        }

        @Autowired
        private VchatUtil vchatUtil;

        @PostMapping("/test")
        public void test(){
            vchatUtil.accessToken();
        }
}

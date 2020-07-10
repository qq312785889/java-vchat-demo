package com.demo.vchat.vchat.controller.user;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

        @Hidden
        @RequestMapping("/toH5")
        public String toH5(){
            return "test/H5pages.html";
        }
}

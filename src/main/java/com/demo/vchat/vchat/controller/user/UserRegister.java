package com.demo.vchat.vchat.controller.user;

import com.demo.vchat.vchat.domain.User;
import com.demo.vchat.vchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserRegister {

    @Autowired
    private UserService userService;

    @RequestMapping("/userRegister")
    @ResponseBody
    public void userRegister(){

    }

}

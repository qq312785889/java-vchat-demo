package com.demo.vchat.vchat.controller.user;

import com.demo.vchat.vchat.domain.HttpResultMassage;
import com.demo.vchat.vchat.rule.user.RegisterDto;
import com.demo.vchat.vchat.service.UserService;
import com.demo.vchat.vchat.util.HttpResultUtil;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/userApi")
@OpenAPIDefinition(
        info = @Info(
                title = "标题",
                version = "1.0",
                description = "接口描述信息"
        )
)
public class UserRegisterController {

    @Autowired
    private UserService userService;

    @Validated
    @RequestMapping(value = "/userRegister",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public HttpResultMassage<RegisterDto> userRegister(@Valid @RequestBody RegisterDto form, BindingResult bindingResult){
        //表单验证
        if(bindingResult.hasErrors()){
            return HttpResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        //验证成功后返回数据
        return HttpResultUtil.success(form);

    }

}

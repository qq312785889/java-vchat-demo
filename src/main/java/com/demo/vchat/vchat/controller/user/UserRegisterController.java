package com.demo.vchat.vchat.controller.user;

import com.demo.vchat.vchat.domain.HttpResultMassage;
import com.demo.vchat.vchat.dto.user.RegisterDto;
import com.demo.vchat.vchat.service.UserService;
import com.demo.vchat.vchat.util.HttpResultUtil;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
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

    @Tag(name = "用户注册",description = "这是用户注册的接口")
    @PostMapping(value = "/userRegister",produces = "application/json")
    public HttpResultMassage<RegisterDto> userRegister(@Valid @RequestBody RegisterDto registerDto, Errors errors){
        //表单验证
        if(errors.hasErrors()){
            return HttpResultUtil.error(1,errors.getFieldError().getDefaultMessage());
        }
        //验证成功后返回数据
        return HttpResultUtil.success(registerDto);

    }

}

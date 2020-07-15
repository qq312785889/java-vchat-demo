package com.demo.vchat.vchat.controller.user;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/loginApi")
@OpenAPIDefinition(
        info = @Info(
                title = "这是login相关的接口",
                version = "1.0",
                description = "这是登录相关的接口信息"

        )
)
public class LoginController {
}

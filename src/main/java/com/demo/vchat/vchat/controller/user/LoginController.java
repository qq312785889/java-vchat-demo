package com.demo.vchat.vchat.controller.user;

import com.demo.vchat.vchat.controller.BaseController;
import com.demo.vchat.vchat.domain.HttpResultMassage;
import com.demo.vchat.vchat.domain.User;
import com.demo.vchat.vchat.dto.user.VchatDto;
import com.demo.vchat.vchat.util.HttpResultUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author 宁缺毋滥
 */
@CrossOrigin
@RestController
@Validated
@RequestMapping("/loginApi")
public class LoginController extends BaseController {

    @PostMapping(value = "/login", produces = "application/json")
    @ResponseBody
    public HttpResultMassage<User> userLogin(@RequestBody @Valid VchatDto vchatDto, Errors errors) {
        if (errors.hasErrors()){
            return HttpResultUtil.error(1,errors.getFieldError().getDefaultMessage());
        }

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(vchatDto.getCode(),"true");

        try {
            subject.login(usernamePasswordToken);
            System.out.println(subject.getPrincipal());
            return HttpResultUtil.success(subject.getPrincipal());
        }catch (UnknownAccountException e){
            return HttpResultUtil.error(2,"账号抛出异常啦");
        }catch (IncorrectCredentialsException e){
            return HttpResultUtil.error(2,"密码抛出异常啦");
        }

    }


}

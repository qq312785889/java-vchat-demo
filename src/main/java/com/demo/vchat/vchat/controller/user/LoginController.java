package com.demo.vchat.vchat.controller.user;

import com.demo.vchat.vchat.domain.HttpResultMassage;
import com.demo.vchat.vchat.domain.OpenId;
import com.demo.vchat.vchat.dto.user.VchatDto;
import com.demo.vchat.vchat.util.HttpResultUtil;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author 宁缺毋滥
 */
@RestController
@Validated
@RequestMapping("/loginApi")
public class LoginController {

    @GetMapping(value = "/login", produces = "application/json")
    public HttpResultMassage<OpenId> userLogin(@RequestBody @Valid VchatDto vchatDto, Errors errors) {
        if (errors.hasErrors()){
            return HttpResultUtil.error(1,errors.getFieldError().getDefaultMessage());
        }
        return null;
    }


}

package com.demo.vchat.vchat.service;

import com.demo.vchat.vchat.domain.User;
import com.demo.vchat.vchat.dto.user.RegisterDto;

public interface UserService {
    /**
     * 账号是否存在
     * @param account
     * @return User
     */
    User userAccount(String account);
    /**
     * 账号注册
     * @param registerDto
     * @return String user_id
     */
    Integer userRegister(RegisterDto registerDto);


}

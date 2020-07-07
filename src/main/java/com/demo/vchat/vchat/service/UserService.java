package com.demo.vchat.vchat.service;

import com.demo.vchat.vchat.domain.User;
import org.apache.ibatis.annotations.Param;
//这的注解没了
public interface UserService {
    /**
     * 账号是否存在
     * @param account
     * @return User
     */
    User userAccount(String account);
    /**
     * 账号注册
     * @param account
     * @param password
     * @return String user_id
     */
    String userRegister(@Param("account") String account, @Param("password") String password);
}

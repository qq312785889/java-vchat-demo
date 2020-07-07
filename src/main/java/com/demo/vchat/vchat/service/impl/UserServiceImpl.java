package com.demo.vchat.vchat.service.impl;

import com.demo.vchat.vchat.domain.User;
import com.demo.vchat.vchat.dto.user.RegisterDto;
import com.demo.vchat.vchat.mapper.user.UserMapper;
import com.demo.vchat.vchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User userAccount(String account) {

        return userMapper.userAccount(account);
    }

    @Override
    public Integer userRegister(RegisterDto registerDto) {
        return userMapper.userRegister(registerDto);
    }
}

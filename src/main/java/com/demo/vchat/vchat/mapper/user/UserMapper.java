package com.demo.vchat.vchat.mapper.user;

import com.demo.vchat.vchat.domain.User;
import com.demo.vchat.vchat.dto.user.RegisterDto;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
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
    //mapper文件中的update,delete,insert语句是不需要设置返回类型的，它们都是默认返回一个int
    Integer userRegister(RegisterDto registerDto);
}

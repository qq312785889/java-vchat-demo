package com.demo.vchat.vchat.realm;

import cn.hutool.core.util.RandomUtil;
import com.demo.vchat.vchat.domain.User;
import com.demo.vchat.vchat.mapper.user.UserMapper;
import com.demo.vchat.vchat.service.UserService;
import com.demo.vchat.vchat.util.VchatUtil;
import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;

/**
 * 自定义Realm
 * （1）AuthenticatingRealm：shiro中的用于进行认证的领域，实现doGetAuthentcationInfo方法实现用户登录时的认证逻辑；
 * （2）AuthorizingRealm：shiro中用于授权的领域，实现doGetAuthrozitionInfo方法实现用户的授权逻辑，AuthorizingRealm继承了AuthenticatingRealm，
 * 所以在实际使用中主要用到的就是这个AuthenticatingRealm类；
 * （3）AuthenticatingRealm、AuthorizingRealm这两个类都是shiro中提供了一些线程的realm接口
 * （4）在与spring整合项目中，shiro的SecurityManager会自动调用这两个方法，从而实现认证和授权，可以结合shiro的CacheManager将认证和授权信息保存在缓存中，
 * 这样可以提高系统的处理效率。
 * @author 宁缺毋滥
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;


    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //doGetAuthorizationInfo方法可能会执行多次  权限判断几次，方法就执行几次
        System.out.println("授权执行开始");

        //给资源进行授权
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        //添加资源的授权字符串
        //authorizationInfo.addStringPermission("user:add");

        //到数据库查询当前用户的授权字符串
        //获取当前登录用户
//        Subject subject = SecurityUtils.getSubject();
//        System.out.println("subject:"+subject);
//        User user = (User) subject.getPrincipal();
//        System.out.println("user:"+user);
//        User dbUser = userService.findById(user.getId());
//
//        authorizationInfo.addStringPermission(dbUser.getPerms());
//
//        return authorizationInfo;
        return null;

    }


    @Override
    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        UsernamePasswordToken usernamePasswordToken =(UsernamePasswordToken) authenticationToken;
        User user = VchatUtil.getOpenId(usernamePasswordToken.getUsername());
        String jwtToken = null;
        if(user.getErrCode() == null) {
            int id;
            //是否为第一次登陆
            User findUser= userMapper.findUserByOpenId(user.getOpenId());
            if (findUser == null){
                id = userMapper.addUser(user);
            }else {
                id=findUser.getId();
            }
            String salt = RandomUtil.randomString(16);
            userMapper.saveSalt(salt, id);
            Signer signer = HMACSigner.newSHA256Signer(salt);
            JWT jwt = new JWT()
                    .setUniqueId("javano1");
            jwt.addClaim("open_id", user.getOpenId());

            jwtToken = id + ":" + JWT.getEncoder().encode(jwt, signer);
        }
        //自定义密码，与前台传值相同  微信登录不需要验证密码
        String password = "true";

        return new SimpleAuthenticationInfo(jwtToken, password, "UserRealm");
    }

}

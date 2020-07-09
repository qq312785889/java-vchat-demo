package com.demo.vchat.vchat.shiro;

import com.demo.vchat.vchat.domain.User;
import com.demo.vchat.vchat.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Realm
 * （1）AuthenticatingRealm：shiro中的用于进行认证的领域，实现doGetAuthentcationInfo方法实现用户登录时的认证逻辑；
 * （2）AuthorizingRealm：shiro中用于授权的领域，实现doGetAuthrozitionInfo方法实现用户的授权逻辑，AuthorizingRealm继承了AuthenticatingRealm，
 * 所以在实际使用中主要用到的就是这个AuthenticatingRealm类；
 * （3）AuthenticatingRealm、AuthorizingRealm这两个类都是shiro中提供了一些线程的realm接口
 * （4）在与spring整合项目中，shiro的SecurityManager会自动调用这两个方法，从而实现认证和授权，可以结合shiro的CacheManager将认证和授权信息保存在缓存中，
 * 这样可以提高系统的处理效率。
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

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

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");

        //编写shiro判断逻辑，判断用户名和密码
        //1.判断用户名  token中的用户信息是登录时候传进来的
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        System.out.println("token:"+token.getUsername());
//        User user = userService.findByName(token.getUsername());
//        if (user==null){
//            //用户名不存在
//            return null;//shiro底层会抛出UnknowAccountException
//        }


        //2.判断密码
        //第二个字段是user.getPassword()，注意这里是指从数据库中获取的password。第三个字段是realm，即当前realm的名称。
        //这块对比逻辑是先对比username，但是username肯定是相等的，所以真正对比的是password。
        //从这里传入的password（这里是从数据库获取的）和token（filter中登录时生成的）中的password做对比，如果相同就允许登录，
        // 不相同就抛出IncorrectCredentialsException异常。
        //如果认证不通过，就不会执行下面的授权方法了
        //return new SimpleAuthenticationInfo(user,user.getPassword(),"UserRealm");
        return null;
    }
}

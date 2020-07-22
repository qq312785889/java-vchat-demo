package com.demo.vchat.vchat.shiro;

import com.demo.vchat.vchat.realm.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro 配置类
 *
 * 关于Configuration的讲解，可参考一下博客：https://www.cnblogs.com/WUXIAOCHANG/p/10877266.html
 */
@Configuration
public class ShiroConfig {


    @Bean
    /*
     * 创建ShiroFilterFactoryBean
     *
     * 一个接口有多个实现类，@Qualifier指明@Autowired具体注入哪个实现类
     * */
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier(value = "securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加Shiro内置管理器
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         *      常用的过滤器
         *      anon：无需认证（登录）即可访问
         *      authc：必须认证才可访问
         *      user：如果使用rememberMe的功能可以直接访问
         *      perms：该资源必须得到资源权限才可访问
         *      role：该资源必须得到角色权限才可访问
         */
        Map<String,String> filterMap = new LinkedHashMap<String,String>();
        filterMap.put("/userApi/userRegister","anon");
        filterMap.put("/toH5","anon");
        filterMap.put("/userApi/getAccessToken","anon");
        filterMap.put("/testWx","anon");
        filterMap.put("/userApi/*","authc");

        //授权过滤器
        //注意：当前授权拦截后，shiro会自动跳转到未授权页面
        //perms[]中的内容是权限值
        /**
         * 权限标识符号规则：【中间用“:”（半角冒号分隔）】
         *         |---资源:操作【user:create：表示对用户资源进行create操作】【等价于：user:create:*（对所有的用户实例进行操作）】
         *         |---资源:操作:实例【user:create:01：表示对用户资源的01实例进行create操作】
         *         |
         *         |---例子：【user:*:01表示对用户资源的01实例进行所有操作】
         * 资源标识符：操作，即是资源级别的粒度；
         */

        //修改调整的登录页
        shiroFilterFactoryBean.setLoginUrl("/errorApi/toLogin");
        //设置未授权的提示页
        shiroFilterFactoryBean.setUnauthorizedUrl("/errorApi/permissionError");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;

    }

    /**
     * 创建DefaultWebSecurityManager类
     * SecurityManager：它是Shiro框架的核心，典型的Facade模式，Shiro通过SecurityManager来管理内部组件实例，并通过它来提供安全管理的各种服务。
     * 里面主要定义了登录、创建subject、登出等操作
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier(value = "userRealm") UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    /**
     * 创建realm
     * Realm充当了Shiro与应用安全数据间的“桥梁”或者“连接器”。也就是说，当对用户执行认证（登录）和授权（访问控制）验证时，Shiro会从应用配置的Realm中查找
     * 用户及其权限信息。
     * 从这个意义上讲，Realm实质上是一个安全相关的DAO：它封装了数据源的连接细节，并在需要时将相关数据提供给Shiro。当配置Shiro时，你必须至少指定一个
     * Realm，用于认证和（或）授权。配置多个Realm是可以的，但是至少需要一个。
     * Shiro内置了可以连接大量安全数据源（又名目录）的Realm，如LDAP、关系数据库（JDBC）、类似INI的文本配置资源以及属性文件等。如果缺省的Realm
     * 不能满足需求，你还可以插入代表自定义数据源的自己的Realm实现。
     */
    @Bean(name="userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }




}

package com.demo.vchat.vchat.interceptor;

import com.demo.vchat.vchat.mapper.user.UserMapper;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 自定义拦截器
 *      继承HandlerInterceptorAdapter
 *      preHandle：进入到控制器方法之前执行的内容
 *          boolean：
 *              true：可以继续执行控制器方法
 *              false：请求被拦截了
 *      postHandler：执行控制器方法之后执行的内容
 *      afterCompletion：响应结束之前执行的内容
 * 操作：
 *     1、统一用户权限校检
 *     2、判断用户是否具有访问当前接口的权限
 * @author 宁缺毋滥
 */
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {
    /**
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        //使用limit，最多分割成2个字符串
        String[] strarray=authorization.split(":",2);
        int id = Integer.parseInt(strarray[0]);
        String salt = userMapper.findSaltById(id);
        Verifier verifier = HMACVerifier.newVerifier(salt);
        try {
            JWT decode = JWT.getDecoder().decode(strarray[1],verifier);
            if ("javano1".equals(decode.uniqueId)){
                return true;
            }
            response.addHeader("errorCode","-1");
            return false;
        }catch (Exception e){
            return false;
        }

        //super.preHandle(request, response, handler)

    }
}

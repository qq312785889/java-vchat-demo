package com.demo.vchat.vchat.interceptor;

import io.fusionauth.jwt.domain.JWT;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class JwtInterceptor extends HandlerInterceptorAdapter {
    /**
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        JWT decode = JWT.getDecoder().decode(authorization);


        return super.preHandle(request, response, handler);
    }
}

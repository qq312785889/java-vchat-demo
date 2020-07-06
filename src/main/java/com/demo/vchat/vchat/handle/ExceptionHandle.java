package com.demo.vchat.vchat.handle;

import com.demo.vchat.vchat.domain.HttpResultMassage;
import com.demo.vchat.vchat.exception.CheckDataException;
import com.demo.vchat.vchat.util.HttpResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;

/*@ControllerAdvice 定义全局异常处理类*/
@ControllerAdvice
public class ExceptionHandle {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    public HttpResultMassage handle(Exception e){
        if (e instanceof CheckDataException){
            CheckDataException checkDataException = (CheckDataException) e;
            return HttpResultUtil.error(checkDataException.getCode(),checkDataException.getMessage());
        }else{
            logger.error("【系统异常】{}",e);
            return HttpResultUtil.error(-1,"未知错误");
        }
    }
}

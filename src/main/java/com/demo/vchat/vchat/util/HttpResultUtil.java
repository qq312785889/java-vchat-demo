package com.demo.vchat.vchat.util;

import com.demo.vchat.vchat.domain.HttpResultMassage;

public class HttpResultUtil {
    public static HttpResultMassage success(Object object){
        HttpResultMassage httpResultMassage = new HttpResultMassage();
        httpResultMassage.setCode(0);
        httpResultMassage.setMsg("成功");
        httpResultMassage.setData(object);
        return httpResultMassage;
    }
    public static HttpResultMassage success(){
        return success(null);
    }
    public static HttpResultMassage error(Integer code,String msg){
        HttpResultMassage httpResultMassage = new HttpResultMassage();
        httpResultMassage.setCode(code);
        httpResultMassage.setMsg(msg);
        return httpResultMassage;
    }
}

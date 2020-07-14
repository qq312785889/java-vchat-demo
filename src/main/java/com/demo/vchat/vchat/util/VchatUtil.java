package com.demo.vchat.vchat.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.demo.vchat.vchat.domain.AccessToken;
import com.demo.vchat.vchat.domain.JSTicket;
import com.demo.vchat.vchat.mapper.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.util.Date;

import static cn.hutool.crypto.SecureUtil.sha1;
import static jdk.internal.org.objectweb.asm.Type.getType;

@Component
public class VchatUtil {

    @Autowired
    private UserMapper userMapper;

    //获取accessToken
    public AccessToken getAccessToken(){
        String appid = "wxac8dab71e58f9d77";//wxac8dab71e58f9d77
        String secret = "d317aa1ab6e7ce1803485f14162f96ce";//d317aa1ab6e7ce1803485f14162f96ce
        String param = "?grant_type=client_credential&appid="+appid+"&secret="+secret;
        String url = "https://api.weixin.qq.com/cgi-bin/token"+param;
        JSONObject jsonObject = doGetStr(url);
        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(jsonObject.getString("access_token"));
        accessToken.setExpiresIn(jsonObject.getInteger("expires_in"));
        JSTicket jsTicket = getJSTicket(accessToken.getAccessToken());
        accessToken.setJsapiTicket(jsTicket.getTicket());
        return accessToken;
    }

    //获取jsapi_ticket
    public JSTicket getJSTicket(String accessToken){
        String param = "?access_token="+accessToken+"&type=jsapi";
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket"+param;
        JSONObject jsonObject = doGetStr(url);
        JSTicket jsTicket = new JSTicket();
        jsTicket.setErrCode(jsonObject.getInteger("errcode"));
        jsTicket.setErrMsg(jsonObject.getString("errmsg"));
        jsTicket.setExpiresIn(jsonObject.getInteger("expires_in"));
        jsTicket.setTicket(jsonObject.getString("ticket"));
        return jsTicket;
    }


    //判断数据库中accessToken和JSTicket是否存在和过期
    public void accessToken(){
        AccessToken accessToken = userMapper.getAccessToken();
        if (accessToken == null){
            System.out.println("accessToken为空，进行创建");
            AccessToken token = getAccessToken();
            System.out.println("输出token："+token);
            System.out.println("输出token值："+token.getAccessToken());
            System.out.println("输出token类型："+getType(token.getAccessToken()));
            System.out.println("...类型出错啦，转成String类型吧！");
            String str = token.getAccessToken();
            System.out.println("输出str类型："+getType(str));
            System.out.println("开始写入数据库！");
            userMapper.saveAccessToken(str,token.getJsapiTicket());
            System.out.println("写入完成啦！");
        }else {
            //判断是否过期
            Date endTime = accessToken.getExpiresTime();
            Date startTime = accessToken.getSaveTime();
            String str = DateUtil.now();
            Date nowDate = DateUtil.parseDate(str);
            boolean flag = DateUtil.isIn(nowDate,startTime,endTime);
            if (!flag){
                //重新获取
                userMapper.clearAccessToken();
                accessToken();
            }



        }
    }

    //RestTemplate 使用get发送请求
    public static JSONObject doGetStr(String url){
        RestTemplate restTemplate = new RestTemplate();//发送request请求
        String response = restTemplate.getForObject(url,String.class);
        JSONObject jsonObject = JSONObject.parseObject(response);
        return jsonObject;
    }

    //hutool 生成随机字符串  只包含数字和字母
    public String randomStr(){
        return RandomUtil.randomString(16);
    }

    //生成时间戳
    public long timestamp(){
        return System.currentTimeMillis()/ 1000;
    }

    public static void main(String[] args) {
//        String date = "2020-07-13 17:08:00";
//        Date date1 = DateUtil.parseDateTime(date);
//        System.out.println(date1);
//        String str = DateUtil.now();
//        Date nowDate = DateUtil.parseDateTime(str);
//        System.out.println(nowDate);
//        long betweenDays = DateUtil.isIn()
//        System.out.println(betweenDays);
    }







}
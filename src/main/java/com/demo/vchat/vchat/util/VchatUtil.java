package com.demo.vchat.vchat.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.demo.vchat.vchat.config.VchatDate;
import com.demo.vchat.vchat.domain.AccessToken;
import com.demo.vchat.vchat.domain.JSTicket;
import com.demo.vchat.vchat.domain.OpenId;
import com.demo.vchat.vchat.mapper.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 宁缺毋滥
 */
@Component
public class VchatUtil {

    @Autowired
    private UserMapper userMapper;

    private static VchatUtil vchatUtil;

    @PostConstruct
    public void init(){
        vchatUtil = this;
        vchatUtil.userMapper = this.userMapper;
    }

    /**
     * 获取accessToken
     * @return accessToken
     */
    public static AccessToken getAccessToken(){
        String param = "?grant_type=client_credential&appid="+VchatDate.APPID+"&secret="+VchatDate.SECRET;
        String url = "https://api.weixin.qq.com/cgi-bin/token"+param;
        JSONObject jsonObject = doGetStr(url);
        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(jsonObject.getString("access_token"));
        accessToken.setExpiresIn(jsonObject.getInteger("expires_in"));
        JSTicket jsTicket = getJSTicket(accessToken.getAccessToken());
        accessToken.setJsapiTicket(jsTicket.getTicket());
        return accessToken;
    }

    /**
     * 获取jsapi_ticket
     * @param accessToken
     * @return jsTicket
     */
    public static JSTicket getJSTicket(String accessToken){
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

    /**
     * 获取openid
     * @param code
     * @return OpenId
     */
    public static OpenId getOpenId(String code){
        String param = "?appid="+VchatDate.APPID+"&secret="+VchatDate.SECRET+"&js_code="+code+"&grant_type=authorization_code";
        String url = "https://api.weixin.qq.com/sns/jscode2session"+param;
        JSONObject jsonObject = doGetStr(url);
        OpenId openId = new OpenId();
        openId.setOpenId(jsonObject.getString("openid"));
        openId.setSessionKey(jsonObject.getString("session_key"));
        openId.setUnionId(jsonObject.getString("unionid"));
        openId.setErrCode(jsonObject.getInteger("errcode"));
        openId.setErrMsg(jsonObject.getString("errmsg"));
        return openId;
    }




    /**
     * 判断数据库中accessToken和JSTicket是否存在和过期
     * @return accessToken
     */
    public static AccessToken accessToken() {
        AccessToken accessToken = vchatUtil.userMapper.getAccessToken();
        if (accessToken == null){
            AccessToken token = getAccessToken();
            String str = token.getAccessToken();
            vchatUtil.userMapper.saveAccessToken(str,token.getJsapiTicket());
            accessToken();
        }else {
            //判断是否过期
            Date expiresTime = accessToken.getExpiresTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time1 = simpleDateFormat.format(expiresTime);
            Date endTime = DateUtil.parseDateTime(time1);
            Date saveTime = accessToken.getSaveTime();
            String time2 = simpleDateFormat.format(saveTime);
            Date startTime = DateUtil.parseDateTime(time2);
            String str = DateUtil.now();
            Date nowDate = DateUtil.parseDateTime(str);
            boolean flag = DateUtil.isIn(nowDate,startTime,endTime);
            if (!flag){
                //重新获取
                vchatUtil.userMapper.clearAccessToken();
                accessToken();
            }
        }
        return accessToken;
    }

    /**
     * RestTemplate 使用get发送请求
     * @param url
     * @return
     */
    public static JSONObject doGetStr(String url){
        //发送request请求
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url,String.class);
        JSONObject jsonObject = JSONObject.parseObject(response);
        return jsonObject;
    }

    /**
     * hutool 生成随机字符串  只包含数字和字母
     * @return 随机字符串  只包含数字和字母
     */
    public static String randomStr(){
        return RandomUtil.randomString(16);
    }

    /**
     * 生成时间戳
     * @return 时间戳
     */
    public static long timestamp(){
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

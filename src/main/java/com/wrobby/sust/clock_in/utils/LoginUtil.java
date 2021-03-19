package com.wrobby.sust.clock_in.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.wrobby.sust.clock_in.entity.UserDetail;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
@Log
/*
登录工具,获取access_token用于获取验证信息
 */
public class LoginUtil {

    public String login(UserDetail userDetail){
        String url="https://mobile.yiban.cn/api/v3/passport/login";
        Map<String,Object> data=new HashMap<>();
        data.put("mobile",userDetail.getUserName());
        data.put("password",userDetail.getUserPassword());
        data.put("imei",userDetail.getImei());//"856451358678456153867"
        String response = HttpUtil.get(url, data);
        JSON json = JSONUtil.parse(response);
        JSON data1 = json.getByPath("data", JSON.class);
        String web_accesstoken_login_url = data1.getByPath("web_accesstoken_login_url",String.class);
       // int start=web_accesstoken_login_url.indexOf("=");
       // String access_token=web_accesstoken_login_url.substring(start+1);
      /*
      修改,返回web_accesstoken_login_url
       */
        return web_accesstoken_login_url;
    }

}

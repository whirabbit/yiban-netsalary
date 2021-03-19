package com.wrobby.sust.clock_in.utils;

import cn.hutool.http.HttpRequest;
import com.wrobby.sust.clock_in.entity.UserToken;
import com.wrobby.sust.clock_in.mapper.UserTokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class PostUtil {
    UserTokenMapper userTokenMapper;
    @Autowired
    public PostUtil(UserTokenMapper userTokenMapper) {
        this.userTokenMapper = userTokenMapper;
    }
/*
三个必要cookie
YB_SSID,yiban_user_token,waf_cookie
 */
    String execute(String url, Map<String,Object> map,String cookie) {

     //   System.out.println(cookie);
        return HttpRequest.post(url).cookie(cookie)
                .header("Connection", "keep-alive")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8")
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("Accept", "application/json, text/javascript, */*; q=0.01")
                .header("User-Agent", "Mozilla/5.0 (Linux; Android 5.1; OPPO R9tm Build/LMY47l; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/43.0.2357.121 Mobile Safari/537.36 yiban_android")
                .header("X-Requested-With", "XMLHttpRequest")
                .header("Host", "www.yiban.cn")
                .header("Origin", "https://www.yiban.cn")
              //  .header("Referer","https://www.yiban.cn/vote/Index/index/groupid/1172607/puserid/7363251")
                .header("sec-ch-ua", "\"Google Chrome\";v=\"87\", \" Not;A Brand\";v=\"99\", \"Chromium\";v=\"87\"")
                .header("sec-ch-ua-mobile", "?0")
                .header("Sec-Fetch-Dest", "empty")
                .header("Sec-Fetch-Mode", "cors")
                .header("Sec-Fetch-Site", "same-origin")
                .form(map)
                .execute().body();

    }
}

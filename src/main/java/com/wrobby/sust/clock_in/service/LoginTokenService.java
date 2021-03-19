package com.wrobby.sust.clock_in.service;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.wrobby.sust.clock_in.entity.UserDetail;
import com.wrobby.sust.clock_in.entity.UserToken;
import com.wrobby.sust.clock_in.mapper.UserDetailMapper;
import com.wrobby.sust.clock_in.mapper.UserTokenMapper;
import com.wrobby.sust.clock_in.utils.LoginUtil;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class LoginTokenService {
    //这里的account_token不是登录得到
    private static String URL="https://y.yiban.cn/vote/index/index?groupid=1172607&puserid=7363251&access_token=";
    UserDetailMapper userDetailMapper;
    UserTokenMapper userTokenMapper;
    LoginUtil loginUtil;
@Autowired
    public LoginTokenService(UserDetailMapper userDetailMapper, UserTokenMapper userTokenMapper,LoginUtil loginUtil) {
        this.userDetailMapper = userDetailMapper;
        this.userTokenMapper = userTokenMapper;
        this.loginUtil=loginUtil;
    }
/*
每隔5天更新一次token
 */

  //  @Scheduled(cron = "0 0 1 1/5 * ? ")
    public void putLoginToken(){
    log.info("开始token更新");
        List<UserDetail> userDetails = userDetailMapper.selectList(null);
        userDetails.forEach(
                userDetail -> {
                    try {
                        log.info(userDetail.getUserName() + "开始更新token");
                        UserToken token = new UserToken();
                        token.setId(userDetail.getId());

                        String account_token_url = loginUtil.login(userDetail);
                        List<Map<String, List<String>> > list=new ArrayList<>();
                        //获取yb_token user_token
                        HttpResponse response = HttpRequest.get(account_token_url)
                                .execute();
                        list.add(response.headers());
                        //获取waf
                     //   HttpResponse execute = HttpRequest.post("http://www.yiban.cn/statistics-gongjian/js?app=yforum").execute();
                       // list.add( execute.headers());
                        StringBuffer buffer=new StringBuffer();
                        list.forEach(cookieStr-> cookieStr.forEach((key, value) -> {

                            if ("Set-Cookie".equals(key)) {
                                value.forEach(v->{
                                    String[] split = v.split(";");
                                    System.out.println(split[0]);
                                    buffer.append(split[0]);
                                    buffer.append(";");
                                });
                            }
                        }));
                        //将account_token加入
                         int start=account_token_url.indexOf("=");
                         String account_token=account_token_url.substring(start+1);
                        buffer.append("loginToken="+account_token);
                        buffer.append(";");
                        System.out.println(buffer.toString());

                        token.setToken(buffer.toString());

                        userTokenMapper.updateById(token);
                       // userTokenMapper.selectList(null).forEach(System.out::println);

                    }catch (Exception e){
                        log.warn(userDetail.getUserName()+"更新出现问题");
                        e.printStackTrace();
                    }
                }
        );
    }
}

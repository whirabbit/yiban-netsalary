package com.wrobby.sust.clock_in;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wrobby.sust.clock_in.entity.UserDetail;
import com.wrobby.sust.clock_in.mapper.UserDetailMapper;
import com.wrobby.sust.clock_in.service.LoginTokenService;
import com.wrobby.sust.clock_in.utils.LoginUtil;
import com.wrobby.sust.clock_in.utils.SendVoteUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class AccoutTest {
    @Autowired
    LoginUtil loginUtil;
    @Autowired
    UserDetailMapper userDetailMapper;
    @Test
    void test01(){
        UserDetail userDetail = userDetailMapper.selectById(1);
        System.out.println(userDetail);
        String loginTokenUrl = loginUtil.login(userDetail);
//        System.out.println();
        HttpResponse execute = HttpRequest.get(loginTokenUrl).execute();
        Map<String, List<String>> headers = execute.headers();
        headers.forEach((key,value)->{
            System.out.println("----------");
            System.out.println(key+value);
        });
        System.out.println(headers);
    }
    @Autowired
    LoginTokenService loginTokenService;
    @Autowired
    SendVoteUtil sendVoteUtil;
    @Test
    void loginTest(){
//        loginTokenService.putLoginToken();
//        sendVoteUtil.send();
        QueryWrapper<UserDetail> queryWrapper=new QueryWrapper<>() ;
        queryWrapper.eq("userName","13101055830");
        UserDetail userDetail = userDetailMapper.selectOne(queryWrapper);
        System.out.println(userDetail);
    }
}

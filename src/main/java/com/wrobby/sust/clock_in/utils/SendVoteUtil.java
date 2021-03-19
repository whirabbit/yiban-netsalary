package com.wrobby.sust.clock_in.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.wrobby.sust.clock_in.entity.UserToken;
import com.wrobby.sust.clock_in.mapper.UserTokenMapper;
import lombok.extern.java.Log;
import org.omg.CORBA.CODESET_INCOMPATIBLE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
@Log
@Component
public class SendVoteUtil {

    SendEmailUtil sendEmailUtil;
    PostUtil postUtil;
    UserTokenMapper userTokenMapper;
@Autowired
    public SendVoteUtil(SendEmailUtil sendEmailUtil, PostUtil postUtil, UserTokenMapper userTokenMapper) {
        this.sendEmailUtil = sendEmailUtil;
        this.postUtil = postUtil;
        this.userTokenMapper = userTokenMapper;
    }

    //https://www.yiban.cn/vote/vote/act //投票地址
    /*
    puid: 7363251
group_id: 1172607
vote_id: 172333802
actor_id: 28383052
voptions_id: 446734658
minimum: 1
scopeMax: 1
     */
    static final String URL="https://www.yiban.cn/vote/vote/add";
   public  boolean send(){
        boolean b=true;//投票发送状态
       //HH使用24进制,hh使用12进制
       SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
       Calendar calendar=Calendar.getInstance();
       /*
       如果是1则代表的是对年份操作，2是对月份操作，3是对星期操作，5是对日期操作，11是对小时操作，12是对分钟操作，13是对秒操作，14是对毫秒操作
       。例如：Calendar calendar = Calendar.getInstance(); calendar .add(5,1);则表示对日期进行加一天操作
        */
       try {
            Map<String, Object> map = new HashMap<>();
            map.put("puid","7363251");
            map.put("scope_ids","1172607");
            map.put("title", "现在是"+format.format(calendar.getTime()));
            map.put("subjectTxt", "开始时间"+format.format( new Date(1611932999)));
            map.put("subjectPic", "");
            map.put("options_num", "3");//投票选项个数,对应subjectTxt_num
            map.put("scopeMin", "1");
            map.put("minimum", "1");
            map.put("scopeMax", "1");
           calendar.add(5,1);
              map.put("voteValue",format.format(calendar.getTime()));//截至时间
            map.put("voteKey", "2");
            map.put("public_type", "4"); //公开方式
            map.put("isAnonymous", "2");//是否匿名投票
            map.put("voteIsCaptcha:", "0");//是否输入验证码
            map.put("istop", "1");
            map.put("sysnotice", "1");
            map.put("isshare", "1");
            map.put("rsa", "1");//加密
            map.put("dom", ".js-submit");
            map.put("group_id","1172607");
            map.put("subjectTxt_1", "同意");
            map.put("subjectTxt_2", "不同意");
            map.put("subjectTxt_3", "同意又不同意");
           List<UserToken> userTokens = userTokenMapper.selectList(null);
           StringBuilder builder=new StringBuilder();
           userTokens.forEach(
                   userToken -> {
                       builder.append(postUtil.execute(URL,map,userToken.getToken()));
                       String content=builder.toString();
                       builder.setLength(0);
           if (JSONUtil.isJson(content)){
               int code;

               try {
                   code=Integer.parseInt((String) JSONUtil.parse(content).getByPath("code"));
               }catch (Exception e){
                   code = (int) JSONUtil.parse(content).getByPath("code");
               }
               if (code==200){
                   // Date date=new Date();
                   // System.out.println("投票发起成功"+content+format.format(date));
                   //sendEmailUtil.send("投票发起成功"+content);
                   log.info("投票发起成功"+content);
               }else {
                   log.warning("id="+userToken.getId()+"有问题"+content);
               }

           }
           });

        }catch (Exception e){
            e.printStackTrace();
            log.info("服务器发起投票异常"+e.getMessage());
           sendEmailUtil.send("服务器发起投票异常"+e.getMessage());
           b=false;
        }
        return  b;}
}

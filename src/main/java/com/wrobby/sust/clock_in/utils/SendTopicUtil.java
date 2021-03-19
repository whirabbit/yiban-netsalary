package com.wrobby.sust.clock_in.utils;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.wrobby.sust.clock_in.entity.UserToken;
import com.wrobby.sust.clock_in.mapper.UserTokenMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Log
@Component
public class SendTopicUtil {
static final String URL="https://www.yiban.cn/forum/article/addAjax";
    SendEmailUtil sendEmailUtil;
    PostUtil postUtil;
    UserTokenMapper userTokenMapper;
    @Autowired
    public SendTopicUtil(SendEmailUtil sendEmailUtil, PostUtil postUtil,UserTokenMapper userTokenMapper) {
        this.sendEmailUtil = sendEmailUtil;
        this.postUtil = postUtil;
        this.userTokenMapper=userTokenMapper;
    }

   public boolean send(){
        boolean b=true;
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calendar=Calendar.getInstance();

        Map<String, Object> map = new HashMap<>();
        map.put("puid","7363251");
        map.put(" pubArea","1172607");
        map.put("title", "报时");
    //   String s = HttpUtil.get("https://www.xzw.com/fortune/capricorn/");
       String s="现在是";
        map.put("content",s+format.format(calendar.getTime()) );
        map.put("dom", ".js-submit");

        try {
            StringBuilder builder=new StringBuilder();
            List<UserToken> userTokens = userTokenMapper.selectList(null);
            userTokens.forEach(userToken -> {
               builder.append(postUtil.execute(URL,map,userToken.getToken()));
               String content=builder.toString();
               builder.setLength(0);
                int code=0;
                try {
                    code =Integer.parseInt((String) JSONUtil.parse(content).getByPath("code")) ;
                }catch (Exception e){
                    code = (int) JSONUtil.parse(content).getByPath("code");
                }
                //int code =Integer.parseInt((String) JSONUtil.parse(content).getByPath("code")) ;

                if (code==200){
                    //sendEmailUtil.send("话题发布成功"+content);
                    // System.out.println("话题发布:"+content+format.format(calendar.getTime()));
                    log.info("话题发布成功"+content);
                }else {
                    log.warning("id="+userToken.getId()+"有问题"+content);
                }

            });


        }catch (Exception e){
            e.printStackTrace();
            log.info("话题发布失败");
            sendEmailUtil.send("服务器话题发布异常"+e.getMessage());
            b=false;
        }
        return b;
    }
}

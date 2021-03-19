package com.wrobby.sust.clock_in;

import cn.hutool.core.date.DateUnit;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.google.gson.JsonObject;
import com.wrobby.sust.clock_in.entity.UserDetail;
import com.wrobby.sust.clock_in.entity.UserToken;
import com.wrobby.sust.clock_in.mapper.UserDetailMapper;
import com.wrobby.sust.clock_in.mapper.UserTokenMapper;
import com.wrobby.sust.clock_in.service.LoginTokenService;
import com.wrobby.sust.clock_in.utils.LoginUtil;
import com.wrobby.sust.clock_in.utils.SendEmailUtil;
import com.wrobby.sust.clock_in.utils.SendTopicUtil;
import com.wrobby.sust.clock_in.utils.SendVoteUtil;
import net.sf.jsqlparser.statement.select.First;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class ClockInApplicationTests {
@Autowired
UserDetailMapper userDetailMapper;
@Autowired
UserTokenMapper userTokenMapper;
    @Test
    void contextLoads() {
//        UserDetail userDetail=new UserDetail();
//        userDetail.setUserName("13101055830");
//        userDetail.setUserPassword("123456789");
//        userDetail.setEmail("2324127579@qq.com");
//        userDetail.setLocation("重庆市忠县");
//        userDetail.setSuccess(0);
//        userDetailMapper.update(userDetail,null);

        List<UserDetail> userDetails = userDetailMapper.selectList(null);
        userDetails.forEach(
                userDetail -> {
                    System.out.println(userDetail);

                }
                );
    }
    @Test
    void get(){
        userTokenMapper.selectList(null).forEach(userToken -> {
            System.out.println(userToken);
        });
    }
    @Test
    void test(){
//GET请求
//        String url="https://www.yiban.cn/my/group";
        String url="https://www.yiban.cn/login/doLoginAjax";
        String password="eWg57LyAgce4ScIiWRNED/gyeCGYAsvE12r0S6ZbvfQvb/dsJ5Ac5LsiSWr2PJMVQk9KzR6pLyxocAgpJ0FdgqHrnXB/GMz9kgwlKsNiJSffYKxVuoYEfijiNcNJXUSxv9+PowKThWKJRckztTqpct5ViGTxkV/UbqNAMyiN2No=";
        Map<String,Object> map = new HashMap<>();
        map.put("account","13101055830");
        map.put("password",password);
        map.put("captcha","范");
        map.put("keysTime","1610806765.07");
        //获取时间戳
        Calendar calendar=new GregorianCalendar();
        map.put("keysTime",calendar.getTime().getTime()+".01");
//        //从后端获取公共密钥
//        String key="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDm4ptKnuteGo8ZH642LLAmACWw" +
//                "KoxNDMn20gi/VzRv6vxr3B7DIALaeR4COZ/I42Y+e9A0BJcE+rnQm8AFRrQEJIb/" +
//                "K8mDJ7N+Ky+pWkeBd7bmaiqF/22aP7oPLv+R+dHR4KToKjUY5ZkMXkgBVxGvcIk1" +
//                "m+dmIHltqocCR9avWwIDAQAB";
        map.put("Cookie","Cookie: YB_SSID=6d1cc1dcbc0eaa027615ba14c57147b2; waf_cookie=3d5c26bc-1521-406a7cbb7bb9831258be70aeff3a47293391; timezone=-8; MESSAGE_NEW_VERSION=1; preview_hidden=0; _YB_OPEN_V2_0=-S200L0lyx7N2Hnp; Hm_lpvt_b03c054f1d2a9d0c3db680e31b6cd6bb=1597540672; Hm_lvt_b03c054f1d2a9d0c3db680e31b6cd6bb=1597540672; CNZZDATA1253488264=1093536048-1594213121-null%7C1599359943; _cnzz_CV1253488264=%E5%AD%A6%E6%A0%A1%E9%A1%B5%E9%9D%A2%7C%3A%2FForum%2FArticle%2Fshow%7C1599364360565%26%E5%AD%A6%E6%A0%A1%E5%90%8D%E7%A7%B0%7C%E5%85%B6%E4%BB%96%7C1599364360566; yiban_user_token=25f08a024702764961dd433d30e06027");
      //  String content = HttpUtil.post(url,map);

        String content = HttpUtil.get(url,map);
        System.out.println(content);
      //  System.out.println(calendar.getTime().getTime());

    }
    @Test
    void test02(){
//        String url="https://www.yiban.cn/my/group";
        //投票
        String url="https://www.yiban.cn/my/publishvote/vote/vote/add";
        Map <String,Object> map=new HashMap<>();

        map.put("puid","7363251");
        map.put("scope_ids","1172607");
        map.put("title","HHRH");
        map.put("subjectTxt","HRBFTRH");
        map.put("subjectPic"," ");
        map.put("options_num","3");//投票选项个数,对应subjectTxt_num
        map.put("scopeMin","1");
        map.put("minimum","1");
        map.put("scopeMax","1");
        map.put("voteValue","2021-01-25 19:40");//截至时间
        map.put("voteKey","2");
        map.put("public_type","0"); //公开方式
        map.put("isAnonymous","2");//是否匿名投票
        map.put("voteIsCaptcha:","0");//是否输入验证码
        map.put("istop","1");
        map.put("sysnotice","2");
        map.put("isshare","1");
        map.put("rsa","1");//加密
        map.put("dom",".js-submit");
        map.put("group_id","1172607");
        map.put("subjectTxt_1","测试");
        map.put("subjectTxt_2","测试");
        map.put("subjectTxt_3","测试");

        String date= HttpRequest.post(url).cookie("YB_SSID=6d1cc1dcbc0eaa027615ba14c57147b2; waf_cookie=3d5c26bc-1521-406a7cbb7bb9831258be70aeff3a47293391; timezone=-8; MESSAGE_NEW_VERSION=1; preview_hidden=0; _YB_OPEN_V2_0=-S200L0lyx7N2Hnp; Hm_lpvt_b03c054f1d2a9d0c3db680e31b6cd6bb=1597540672; Hm_lvt_b03c054f1d2a9d0c3db680e31b6cd6bb=1597540672; CNZZDATA1253488264=1093536048-1594213121-null%7C1599359943; _cnzz_CV1253488264=%E5%AD%A6%E6%A0%A1%E9%A1%B5%E9%9D%A2%7C%3A%2FForum%2FArticle%2Fshow%7C1599364360565%26%E5%AD%A6%E6%A0%A1%E5%90%8D%E7%A7%B0%7C%E5%85%B6%E4%BB%96%7C1599364360566; yiban_user_token=25f08a024702764961dd433d30e06027")
                .header("Connection","keep-alive")
                .header("Accept-Encoding","gzip, deflate, br")
                .header("Accept-Language","zh-CN,zh;q=0.9,en;q=0.8")
                   //.body(mes)
                .form(map)
                .execute().body();
        System.out.println(date);

    }
    @Autowired
    SendEmailUtil sendEmailUtil;
    @Autowired
    SendVoteUtil sendVoteUtil;
    @Autowired
    SendTopicUtil sendTopicUtil;
    @Autowired
    LoginTokenService loginTokenService;
@Test
    void test03(){
   // loginTokenService.putLoginToken();
//loginTokenService.putLoginToken();
    sendTopicUtil.send();
        sendVoteUtil.send();
}
    @Test
    void test04(){
//        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm");
//        Calendar calendar=Calendar.getInstance();
//        System.out.println(format.format(calendar.getTime()));

        String t="{\"code\":200,\"message\":\"\\u64cd\\u4f5c\\u6210\\u529f\",\"data\":{\"link\":\"www.yiban.cn\\/forum\\/article\\/show\\/article_id\\/153029592\\/channel_id\\/957374\\/puid\\/7363251\\/group_id\\/1172607\"}}";
        JSON json = JSONUtil.parse(t);
String s="200";
        System.out.println(json.getByPath("code")=="200");
        System.out.println(json.getByPath("code").equals(s));
        System.out.println(json.getByPath("code").getClass());


    }
    @Autowired
    LoginUtil loginUtil;
    @Test
    void test05(){
       // loginUtil.login();
        //System.out.println(new Date().getTime()/1000);

    }
    @Test
    void test06(){
String url="https://mobile.yiban.cn/api/v3/passport/login";
Map<String,Object> data=new HashMap<>();
data.put("mobile","13101055830");
data.put("password","WC123456789");
data.put("imei","856451358678456153867");
        String s = HttpUtil.get(url, data);
        HttpResponse execute = HttpRequest.get(url).form(data).execute();
        Map<String, List<String>> headers = execute.headers();
        System.out.println("---"+headers+execute.getCookieStr());

        //e0d8511e5056ab6e8a131c3c2c37e025
        //access_token=e0d8511e5056ab6e8a131c3c2c37e025
    }
    @Test
    void  test6_5(){
        String url="https://y.yiban.cn/forum/index/index?groupid=1172607&puserid=7363251&access_token=e0d8511e5056ab6e8a131c3c2c37e025";
        HttpResponse post = HttpRequest.get(url).execute();
        Map<String, List<String>> headers = post.headers();
        headers.forEach((key,value)->{
            if ( "Set-Cookie".equals(key)  )
{
    System.out.println(value);
}

        });

    }
@Test
    void test07(){
        String data="{\"response\":100,\"message\":\"\\u8bf7\\u6c42\\u6210\\u529f\",\"data\":{\"user\":{\"id\":28383052,\"nick\":\"\\u738b\\u9526\\u7a0b\",\"mobile\":\"13101055830\",\"access_token\":\"22830bcb1e42d6a15e1c74c85cdef6de\"},\"isSchoolVerify\":true,\"bindMobile\":false,\"redirect\":\"\",\"web_accesstoken_login_url\":\"http:\\/\\/www.yiban.cn\\/login\\/accessTokenLogin?access_token=22830bcb1e42d6a15e1c74c85cdef6de\",\"universities_import_time_url\":\"http:\\/\\/q.yiban.cn\\/app\\/index\\/appid\\/135575\"}}";
    JSON json = JSONUtil.parse(data);
    JSON data1 = json.getByPath("data", JSON.class);
    String web_accesstoken_login_url = data1.getByPath("web_accesstoken_login_url", String.class);
    int i = web_accesstoken_login_url.indexOf("=");
    System.out.println(web_accesstoken_login_url.substring(i+1));
}

@Test
    void login(){
    loginTokenService.putLoginToken();
//    List<UserDetail> userDetails = userDetailMapper.selectList(null);
//    userDetails.forEach(userDetail -> {
//        String account_token = loginUtil.login(userDetail);
//        System.out.println(account_token);
//        HttpResponse response= HttpRequest.get("https://y.yiban.cn/vote/index/index?groupid=1172607&puserid=7363251&access_token="+account_token)
//                .execute();
//Map<String, List<String>> cookieStr =response.headers();
//      cookieStr.forEach((key,value)->{
//
//if ( "Set-Cookie".equals(key)  )
//{
//    String s = value.toString();
//    System.out.println(s.substring(s.indexOf("[")+1,s.indexOf("]")));
//}
//      });
//        System.out.println("----");
//    });
//
}
@Test
    void test_waf(){
    HttpResponse execute = HttpRequest.post("http://www.yiban.cn/statistics-gongjian/js?app=yforum").execute();
    Map<String, List<String>> cookieStr = execute.headers();
    cookieStr.forEach((key,value)->{
        if ("Set-Cookie".equals(key)){
            value.forEach(s -> {
                String[] split = s.split(";");
                System.out.println(split[0]);
            });
        }
    });
    System.out.println(cookieStr);
}
}

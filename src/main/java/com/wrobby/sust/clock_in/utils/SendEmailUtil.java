package com.wrobby.sust.clock_in.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SendEmailUtil {
    @Autowired
    private JavaMailSender javaMailSender;
    private String from="2728847268@qq.com";
    private  String to="2324127579@qq.com";
     public void send(String message){

        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject("服务器提醒");
        simpleMailMessage.setText(message);
        javaMailSender.send(simpleMailMessage);
    }
}

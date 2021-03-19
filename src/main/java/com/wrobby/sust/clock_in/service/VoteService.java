package com.wrobby.sust.clock_in.service;

import com.wrobby.sust.clock_in.utils.SendEmailUtil;
import com.wrobby.sust.clock_in.utils.SendTopicUtil;
import com.wrobby.sust.clock_in.utils.SendVoteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/*
定期执行投票相关任务
 */
@Service
public class VoteService {

    SendVoteUtil sendVoteUtil;
    SendTopicUtil sendTopicUtil;
    SendEmailUtil sendEmailUtil;
@Autowired
    public VoteService(SendVoteUtil sendVoteUtil, SendTopicUtil sendTopicUtil, SendEmailUtil sendEmailUtil) {
        this.sendVoteUtil = sendVoteUtil;
        this.sendTopicUtil = sendTopicUtil;
        this.sendEmailUtil = sendEmailUtil;
    }



    /*
        每天8 ,12,18发起一个投票0 0 8,12,18 * * ? *
         */
    @Scheduled(cron = "0 0 6,12,18 * * ?")
    void sendVote(){
        sendVoteUtil.send();
        sendTopicUtil.send();
       // sendEmailUtil.send("定时任务执行完成");
    }

    @Scheduled(cron = "0 0 11 * * ?")
    void sendTopic(){

    }
}

package com.wrobby.sust.clock_in.service;

import com.wrobby.sust.clock_in.entity.UserDetail;
import com.wrobby.sust.clock_in.entity.UserToken;
import com.wrobby.sust.clock_in.mapper.UserDetailMapper;
import com.wrobby.sust.clock_in.mapper.UserTokenMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailService {
    UserDetailMapper userDetailMapper;
    UserTokenMapper userTokenMapper;
@Autowired
    public UserDetailService(UserDetailMapper userDetailMapper,UserTokenMapper userTokenMapper) {
        this.userDetailMapper = userDetailMapper;
        this.userTokenMapper=userTokenMapper;
    }
/*
插入新用户
 */
    public Boolean UserDetailSave(UserDetail userDetail){
    boolean result= true;
        int insert = userDetailMapper.insert(userDetail);
    if (insert==0){
         result=false;
         log.warn(userDetail.getUserName()+"信息插入失败");
    }
        return result;
    }
/*
更新用户token
 */
    public Boolean UserTokenUpdate(UserToken userToken){
        boolean result= true;

        int insert = userTokenMapper.update(userToken,null);
        if (insert==0){
            result=false;
            log.warn("id="+userToken.getId()+"信息更新失败");
        }
        return result;
    }
}

package com.wrobby.sust.clock_in.controllor;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wrobby.sust.clock_in.entity.UserDetail;
import com.wrobby.sust.clock_in.entity.UserToken;
import com.wrobby.sust.clock_in.mapper.UserDetailMapper;
import com.wrobby.sust.clock_in.mapper.UserTokenMapper;
import com.wrobby.sust.clock_in.service.UserDetailService;
import com.wrobby.sust.clock_in.utils.SendTopicUtil;
import com.wrobby.sust.clock_in.utils.SendVoteUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserDetailController {
UserDetailService userDetailService;
@Autowired
UserDetailMapper userDetailMapper;
@Autowired
UserTokenMapper userTokenMapper;
@Autowired
    SendVoteUtil sendVoteUtil;
@Autowired
    SendTopicUtil sendTopicUtil;
@Autowired
    public UserDetailController(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }
    @PostMapping("/insert")
    public CommonResult<UserDetail> userInsert(@RequestBody UserDetail userDetail){


    if (userDetailService.UserDetailSave(userDetail)){
        return new CommonResult<>(200,"成功");
    }else {

        return new CommonResult<>(400,"请检查数据是否正确",userDetail);
    }

    }
    @GetMapping("/update/{id}")
    public CommonResult update(@PathVariable("id") Integer id, @RequestParam("v") String token){
    log.info(token);
if (userDetailService.UserTokenUpdate(new UserToken(id,token))){
    return new CommonResult<>(200,"更新成功",userTokenMapper.selectById(id));
}else {
    return new CommonResult<>(111,"更新失败,请查询信息后在更新--/user/{username}");
}
    }
    @GetMapping("/{username}")
    public CommonResult getMessage(@PathVariable String username){
        QueryWrapper<UserDetail> queryWrapper=new QueryWrapper<>() ;
        queryWrapper.eq("user_name",username);
        UserDetail userDetail = userDetailMapper.selectOne(queryWrapper);
        return new CommonResult <>(200,"成功",userDetail);
    }
    @GetMapping("/do")
    public String get(){
        sendVoteUtil.send();
        sendTopicUtil.send();
    return  "成功";
    }

}

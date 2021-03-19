package com.wrobby.sust.clock_in.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wrobby.sust.clock_in.entity.UserToken;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserTokenMapper extends BaseMapper<UserToken> {
}

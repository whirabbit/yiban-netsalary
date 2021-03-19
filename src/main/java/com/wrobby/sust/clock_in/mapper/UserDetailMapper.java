package com.wrobby.sust.clock_in.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wrobby.sust.clock_in.entity.UserDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDetailMapper extends BaseMapper<UserDetail> {
}

package com.wrobby.sust.clock_in.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class UserToken {
    /*
    通过主键关联token信息
     */
    private Integer id;
    private String token;
    @TableField(fill = FieldFill.INSERT)
    private Date creatTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    public UserToken(Integer id, String token) {
        this.id = id;
        this.token = token;
    }
}

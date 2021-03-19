package com.wrobby.sust.clock_in.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class UserDetail {
    @TableId(type=IdType.AUTO)
    private Integer id;
    private  String userName;
    private String userPassword;
    private  String location;
    private  Integer success;
    private String email;
    private  String imei;
    @TableField(fill = FieldFill.INSERT)
    private Date creatTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}

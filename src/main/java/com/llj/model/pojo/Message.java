package com.llj.model.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    Long id;
    String content;
    Integer isReaded;
    Long receiverId;
    Long milliSeconds; //当前时间毫秒数
    @TableField(fill= FieldFill.INSERT)
    Long createUser;
    @TableField(fill= FieldFill.INSERT)
    LocalDateTime createTime;
}

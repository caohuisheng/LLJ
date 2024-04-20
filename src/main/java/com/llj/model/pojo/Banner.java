package com.llj.model.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Banner {
    Long id;
    String picture;
    String url;
    @TableField(fill = FieldFill.INSERT)
    Long createUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    Long updateUser;
    @TableField(fill = FieldFill.INSERT)
    LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    LocalDateTime updateTime;
}

package com.llj.model.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 推荐内容
 */
@Data
@TableName("suggest_content")
public class Suggest {
    private Long id;
    private String title;
    private String picture;
    private String content;
    private String author;
    //0：大学生内容 1：老年人类容
    private Integer type;
    private Integer remarkNum;

    @TableField(fill=FieldFill.INSERT)
    private Long createUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
    @TableField(fill=FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill=FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}

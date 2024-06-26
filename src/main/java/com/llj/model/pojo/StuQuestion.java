package com.llj.model.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 大学生问题
 */
@Data
public class StuQuestion {
    private Long id;
    private String ques;
    private String details;
    private String tagList; //标签列表（json格式）

    @TableField(fill=FieldFill.INSERT)
    private Long createUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
    @TableField(fill=FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill=FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @Data
    @TableName("question_tag")
    public static class Tag {
        private Long id;
        private String name;

        @TableField(fill= FieldFill.INSERT)
        private Long createUser;
        @TableField(fill = FieldFill.INSERT_UPDATE)
        private Long updateUser;
        @TableField(fill=FieldFill.INSERT)
        private LocalDateTime createTime;
        @TableField(fill=FieldFill.INSERT_UPDATE)
        private LocalDateTime updateTime;
    }

    @Data
    @TableName("question_liker")
    public static class Liker {
        Long id;
        Long questionId;
        @TableField(fill= FieldFill.INSERT)
        private Long createUser;
        @TableField(fill=FieldFill.INSERT)
        private LocalDateTime createTime;
    }

    @Data
    @TableName("question_follower")
    public static class Follower {
        private Long id;
        private Long questionId;
        @TableField(fill= FieldFill.INSERT)
        private Long createUser;
        @TableField(fill=FieldFill.INSERT)
        private LocalDateTime createTime;
    }

    @Data
    @TableName("question_answer")
    public static class Answer {
        Long id;
        Long questionId;
        String answer;

        @TableField(fill= FieldFill.INSERT)
        private Long createUser;
        @TableField(fill=FieldFill.INSERT)
        private LocalDateTime createTime;
    }
}

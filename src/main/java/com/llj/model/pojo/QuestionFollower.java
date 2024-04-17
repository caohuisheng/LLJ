package com.llj.model.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 大学生问题关注者
 */
@Data
public class QuestionFollower {
    private Long id;
    private Long question_id;
    @TableField(fill= FieldFill.INSERT)
    private Long createUser;
    @TableField(fill=FieldFill.INSERT)
    private LocalDateTime createTime;
}

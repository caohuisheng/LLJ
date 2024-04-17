package com.llj.model.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 大学生问题回答
 */
@Data
public class QuestionAnswer {
    Long id;
    Long question_id;
    String answer;

    @TableField(fill= FieldFill.INSERT)
    private Long createUser;
    @TableField(fill=FieldFill.INSERT)
    private LocalDateTime createTime;
}

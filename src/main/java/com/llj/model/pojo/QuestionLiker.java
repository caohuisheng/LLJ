package com.llj.model.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

/*
* 大学生问题点赞者
*/
@Data
public class QuestionLiker {
    Long id;
    Long question_id;
    @TableField(fill= FieldFill.INSERT)
    private Long createUser;
    @TableField(fill=FieldFill.INSERT)
    private LocalDateTime createTime;
}

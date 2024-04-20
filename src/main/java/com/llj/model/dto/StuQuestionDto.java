package com.llj.model.dto;

import com.llj.model.pojo.StuQuestion;
import lombok.Data;

@Data
public class StuQuestionDto extends StuQuestion {
    private Integer likerTotal; //点赞总数
    private Integer followerTotal;  //关注总数
    private Integer answerTotal; //回答总数
}

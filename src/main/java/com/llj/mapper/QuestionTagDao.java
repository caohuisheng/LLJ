package com.llj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.llj.model.pojo.StuQuestion;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionTagDao extends BaseMapper<StuQuestion.Tag> {
}

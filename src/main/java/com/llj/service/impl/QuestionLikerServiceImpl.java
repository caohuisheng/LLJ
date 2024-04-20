package com.llj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llj.mapper.QuestionLikerDao;
import com.llj.mapper.QuestionTagDao;
import com.llj.model.pojo.StuQuestion;
import com.llj.service.IQuestionLikerService;
import com.llj.service.IQuestionTagService;
import org.springframework.stereotype.Service;

@Service
public class QuestionLikerServiceImpl extends ServiceImpl<QuestionLikerDao, StuQuestion.Liker> implements IQuestionLikerService {
}

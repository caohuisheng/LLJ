package com.llj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llj.mapper.QuestionAnswerDao;
import com.llj.mapper.QuestionLikerDao;
import com.llj.model.pojo.StuQuestion;
import com.llj.service.IQuestionAnswerService;
import com.llj.service.IQuestionLikerService;
import org.springframework.stereotype.Service;

@Service
public class QuestionAnswerServiceImpl extends ServiceImpl<QuestionAnswerDao, StuQuestion.Answer> implements IQuestionAnswerService {
}

package com.llj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llj.mapper.QuestionFollowerDao;
import com.llj.mapper.QuestionLikerDao;
import com.llj.model.pojo.StuQuestion;
import com.llj.service.IQuestionFollowerService;
import com.llj.service.IQuestionLikerService;
import org.springframework.stereotype.Service;

@Service
public class QuestionFollowerServiceImpl extends ServiceImpl<QuestionFollowerDao, StuQuestion.Follower> implements IQuestionFollowerService {
}

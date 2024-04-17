package com.llj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llj.mapper.QuestionTagDao;
import com.llj.model.pojo.QuestionTag;
import com.llj.service.IQuestionTagService;
import org.springframework.stereotype.Service;

@Service
public class QuestionTagServiceImpl extends ServiceImpl<QuestionTagDao, QuestionTag> implements IQuestionTagService {
}

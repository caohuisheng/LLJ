package com.llj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llj.mapper.*;
import com.llj.model.dto.StuQuestionDto;
import com.llj.model.pojo.StuQuestion;
import com.llj.service.IStuQuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StuQuestionServiceImpl extends ServiceImpl<StuQuestionDao,StuQuestion> implements IStuQuestionService {
    @Autowired
    private QuestionFollowerDao questionFollowerDao;
    @Autowired
    private QuestionLikerDao questionLikerDao;
    @Autowired
    private QuestionAnswerDao questionAnswerDao;

    /**
     * 根据id删除问题，并删除对应的关注，点赞，回答
     * @param id
     * @return
     */
    @Override
    public boolean removeById(Serializable id) {
        super.removeById(id);
        LambdaQueryWrapper<StuQuestion.Follower> lqw1=new LambdaQueryWrapper<>();
        lqw1.eq(StuQuestion.Follower::getQuestionId,id);
        questionFollowerDao.delete(lqw1);
        LambdaQueryWrapper<StuQuestion.Liker> lqw2=new LambdaQueryWrapper<>();
        lqw2.eq(StuQuestion.Liker::getQuestionId,id);
        questionLikerDao.delete(lqw2);
        LambdaQueryWrapper<StuQuestion.Answer> lqw3=new LambdaQueryWrapper<>();
        lqw3.eq(StuQuestion.Answer::getQuestionId,id);
        questionAnswerDao.delete(lqw3);
        return true;
    }

    private StuQuestionDto getQuestionDto(StuQuestion question){
        StuQuestionDto dto=new StuQuestionDto();
        BeanUtils.copyProperties(question,dto);

        LambdaQueryWrapper<StuQuestion.Follower> lqw1 = new LambdaQueryWrapper<>();
        lqw1.eq(StuQuestion.Follower::getQuestionId,question.getId());
        dto.setFollowerTotal(questionFollowerDao.selectCount(lqw1));
        LambdaQueryWrapper<StuQuestion.Liker> lqw2 = new LambdaQueryWrapper<>();
        lqw2.eq(StuQuestion.Liker::getQuestionId,question.getId());
        dto.setLikerTotal(questionLikerDao.selectCount(lqw2));
        LambdaQueryWrapper<StuQuestion.Answer> lqw3 = new LambdaQueryWrapper<>();
        lqw3.eq(StuQuestion.Answer::getQuestionId,question.getId());
        dto.setAnswerTotal(questionAnswerDao.selectCount(lqw3));
        return dto;
    }

    @Override
    public IPage<StuQuestionDto> page(String keyword, int start, int pageSize) {
        IPage<StuQuestion> page = new Page<>(start,pageSize);
        LambdaQueryWrapper<StuQuestion> lqw = new LambdaQueryWrapper<>();
        lqw.like(!StringUtils.isBlank(keyword),StuQuestion::getQues,keyword)
                .or()
                .like(!StringUtils.isBlank(keyword),StuQuestion::getDetails,keyword)
                .orderByDesc(StuQuestion::getCreateTime);
        //查询并设置其它信息
        IPage<StuQuestion> result = this.page(page, lqw);
        IPage<StuQuestionDto> result_new = new Page<>();
        BeanUtils.copyProperties(result,result_new);
        List<StuQuestionDto> records_new = result.getRecords().stream().map(ques -> getQuestionDto(ques)).collect(Collectors.toList());
        result_new.setRecords(records_new);
        return result_new;
    }
}

package com.llj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llj.mapper.StuQuestionDao;
import com.llj.model.pojo.StuQuestion;
import com.llj.service.IStuQuestionService;
import org.springframework.stereotype.Service;

@Service
public class StuQuestionServiceImpl extends ServiceImpl<StuQuestionDao,StuQuestion> implements IStuQuestionService {

    @Override
    public IPage<StuQuestion> page(String keyword, int start, int pageSize) {
        IPage<StuQuestion> page = new Page<>(start,pageSize);
        LambdaQueryWrapper<StuQuestion> lqw = new LambdaQueryWrapper<>();
        lqw.like(StuQuestion::getQues,keyword).or().like(StuQuestion::getDetails,keyword);
        return this.page(page,lqw);
    }
}

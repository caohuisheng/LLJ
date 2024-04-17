package com.llj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.llj.model.pojo.StuQuestion;

public interface IStuQuestionService extends IService<StuQuestion> {
    IPage<StuQuestion> page(String keyword, int start, int pageSize);
}

package com.llj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.llj.common.R;
import com.llj.model.pojo.StuQuestion;
import com.llj.service.IQuestionAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question/answer")
public class QuestionAnswerController {

    @Autowired
    private IQuestionAnswerService questionAnswerService;

    @PostMapping
    public R<String> save(@RequestBody StuQuestion.Answer answer){
        questionAnswerService.save(answer);
        return R.success("回答成功!");
    }

    @DeleteMapping("{answerId}")
    public R<String> delete(@PathVariable Long answerId){
        questionAnswerService.removeById(answerId);
        return R.success("删除回答成功!");
    }

    @GetMapping("page")
    public R<IPage> queryByPage(int start,int pageSize){
        IPage<StuQuestion.Answer> page = new Page<>(start,pageSize);
        LambdaQueryWrapper<StuQuestion.Answer> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(StuQuestion.Answer::getCreateTime);
        IPage<StuQuestion.Answer> result = questionAnswerService.page(page, lqw);
        return R.success(result);
    }
}

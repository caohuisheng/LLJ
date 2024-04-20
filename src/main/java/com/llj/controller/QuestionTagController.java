package com.llj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.llj.common.R;
import com.llj.model.pojo.StuQuestion;
import com.llj.service.IQuestionTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question/tag")
public class QuestionTagController {

    @Autowired
    private IQuestionTagService questionTagService;

    @PostMapping
    public R<String> save(@RequestBody StuQuestion.Tag questionTag){
        questionTagService.save(questionTag);
        return R.success("添加问题标签成功！");
    }

    @DeleteMapping("{id}")
    public R<String> delete(@PathVariable Long id){
        questionTagService.removeById(id);
        return R.success("删除问题标签成功！");
    }

    @PutMapping
    public R<String> updateById(@RequestBody StuQuestion.Tag questionTag){
        questionTagService.updateById(questionTag);
        return R.success("修改问题标签成功！");
    }

    @GetMapping("page")
    public R<IPage<StuQuestion.Tag>> page(String keyword,int start,int pageSize){
        IPage<StuQuestion.Tag> page = new Page(start,pageSize);
        LambdaQueryWrapper<StuQuestion.Tag> lqw = new LambdaQueryWrapper<>();
        lqw.like(StringUtils.isNotBlank(keyword),StuQuestion.Tag::getName,keyword).orderByDesc(StuQuestion.Tag::getCreateTime);
        questionTagService.page(page,lqw);
        return R.success(page);
    }
}

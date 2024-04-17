package com.llj.controller;

import com.llj.common.R;
import com.llj.model.pojo.QuestionTag;
import com.llj.service.IQuestionTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tag")
public class QuestionTagController {

    @Autowired
    private IQuestionTagService questionTagService;

    @PostMapping
    public R<String> save(@RequestBody QuestionTag questionTag){
        questionTagService.save(questionTag);
        return R.success("添加问题标签成功！");
    }

    @DeleteMapping("/{id}")
    public R<String> delete(@PathVariable Long id){
        questionTagService.removeById(id);
        return R.success("删除问题标签成功！");
    }

    @PutMapping("/{id}")
    public R<String> updateById(@RequestBody QuestionTag questionTag){
        questionTagService.updateById(questionTag);
        return R.success("修改问题标签成功！");
    }

}

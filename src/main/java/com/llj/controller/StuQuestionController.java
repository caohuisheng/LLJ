package com.llj.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.llj.common.R;
import com.llj.model.pojo.StuQuestion;
import com.llj.service.IStuQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class StuQuestionController {

    @Autowired
    private IStuQuestionService stuQuestionService;

    @PostMapping
    public R<String> save(@RequestBody StuQuestion stuQuestion){
        stuQuestionService.save(stuQuestion);
        return R.success("添加大学生问题成功！");
    }

    @DeleteMapping("/{id}")
    public R<String> delete(@PathVariable Long id){
        stuQuestionService.removeById(id);
        return R.success("删除大学生问题成功！");
    }

    @PutMapping()
    public R<String> update(StuQuestion stuQuestion){
        stuQuestionService.updateById(stuQuestion);
        return R.success("修改大学生问题成功！");
    }

    @GetMapping("/page")
    public R<IPage> page(String keyword,int start,int pageSize){
        IPage<StuQuestion> page = stuQuestionService.page(keyword, start, pageSize);
        return R.success(page);
    }

    @GetMapping("/{id}")
    public R<Object> queryById(@PathVariable("id") Long questionId){
        StuQuestion question = stuQuestionService.getById(questionId);
        return R.success(question);
    }
}

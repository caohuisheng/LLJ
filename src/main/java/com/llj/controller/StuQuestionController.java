package com.llj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.llj.common.BaseContext;
import com.llj.common.R;
import com.llj.model.dto.StuQuestionDto;
import com.llj.model.pojo.StuQuestion;
import com.llj.service.IQuestionAnswerService;
import com.llj.service.IQuestionFollowerService;
import com.llj.service.IQuestionLikerService;
import com.llj.service.IStuQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class StuQuestionController {

    @Autowired
    private IStuQuestionService stuQuestionService;
    @Autowired
    private IQuestionLikerService questionLikerService;
    @Autowired
    private IQuestionFollowerService questionFollowerService;

    @PostMapping
    public R<String> save(@RequestBody StuQuestion stuQuestion){
        StuQuestion ques=new StuQuestion();
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
        IPage<StuQuestionDto> page = stuQuestionService.page(keyword, start, pageSize);
        return R.success(page);
    }

    @GetMapping("/{id}")
    public R<Object> queryById(@PathVariable("id") Long questionId){
        StuQuestion question = stuQuestionService.getById(questionId);
        return R.success(question);
    }

    @PostMapping("/like")
    public R<String> like(StuQuestion.Liker liker){
        questionLikerService.save(liker);
        return R.success("点赞成功!");
    }

    @PostMapping("/unlike")
    public R<String> unlike(Long questionId){
        LambdaQueryWrapper<StuQuestion.Liker> lqw = new LambdaQueryWrapper<>();
        lqw.eq(StuQuestion.Liker::getQuestionId,questionId).eq(StuQuestion.Liker::getCreateUser, BaseContext.getCurrentId());
        questionLikerService.remove(lqw);
        return R.success("取消点赞成功!");
    }

    @PostMapping("/follow")
    public R<String> follow(StuQuestion.Follower follower){
        questionFollowerService.save(follower);
        return R.success("关注成功!");
    }

    @PostMapping("/unfollow")
    public R<String> unfollow(Long questionId){
        LambdaQueryWrapper<StuQuestion.Follower> lqw = new LambdaQueryWrapper<>();
        lqw.eq(StuQuestion.Follower::getQuestionId,questionId).eq(StuQuestion.Follower::getCreateUser, BaseContext.getCurrentId());
        questionFollowerService.remove(lqw);
        return R.success("取消关注成功!");
    }



}

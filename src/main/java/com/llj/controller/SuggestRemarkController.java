package com.llj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.llj.common.BaseContext;
import com.llj.common.R;
import com.llj.mapper.SuggestRemarkDao;
import com.llj.model.pojo.SuggestRemark;
import com.llj.service.ISuggestRemarkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class SuggestRemarkController {
    @Autowired
    private ISuggestRemarkService remarkService;

    @PostMapping("/suggest/remark")
    public R<String> save(@RequestBody SuggestRemark suggestRemark){
        remarkService.save(suggestRemark);
        return R.success("添加推荐内容评论成功！");
    }

    @DeleteMapping("/suggest/remark/{id}")
    public R<String> delete(@PathVariable Long id){
        remarkService.removeById(id);
        return R.success("删除推荐内容评论成功！");
    }

    @GetMapping("/suggest/remark/page")
    public R<IPage<SuggestRemark>> queryByPageAndId(int start,int pageSize){
        IPage<SuggestRemark> page = new Page<>(start,pageSize);
        LambdaQueryWrapper<SuggestRemark> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(SuggestRemark::getCreateTime);
        remarkService.page(page,lqw);
        return R.success(page);
    }
}

package com.llj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.llj.common.R;
import com.llj.model.pojo.Suggest;
import com.llj.service.ISuggestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suggest")
@Slf4j
public class SuggestController {
    @Autowired
    private ISuggestService suggestService;

    @PostMapping
    public R<String> save(@RequestBody Suggest suggest){
        suggestService.save(suggest);
        return R.success("新增推荐内容成功！");
    }

    @DeleteMapping ("/{id}")
    public R<String> delete(@PathVariable Long id){
        suggestService.removeById(id);
        return R.success("删除推荐内容成功！");
    }

    @PutMapping
    public R<String> update(@RequestBody Suggest suggest){
        suggestService.updateById(suggest);
        return R.success("更新推荐内容成功！");
    }

    @GetMapping("/list")
    public R<List<Suggest>> list(){
        List<Suggest> suggestList = suggestService.list();
        return R.success(suggestList);
    }

    @GetMapping("/{id}")
    public R<Suggest> queryById(@PathVariable Long id){
        Suggest suggest = suggestService.getById(id);
        return R.success(suggest);
    }

    /**
     * 分页查询，并按照标题进行模糊查询
     */
    @GetMapping("/page")
    public R<IPage> queryByPageAndCondition(int current,int pageSize,String title,Integer type){
        return suggestService.queryByPageAndCondition(current,pageSize,title,type);
    }

}

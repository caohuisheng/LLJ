package com.llj.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.llj.common.R;
import com.llj.mapper.HotActivityDao;
import com.llj.model.pojo.HotActivity;
import com.llj.service.IHotActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hot_activity")
public class HotActivityController {
    @Autowired
    private IHotActivityService hotActivityService;

    @PostMapping
    public R<String> save(HotActivity hotActivity){
        hotActivityService.save(hotActivity);
        return R.success("添加热门活动成功！");
    }

    @DeleteMapping("/{id}")
    public R<String> deleteById(@PathVariable Long id){
        hotActivityService.removeById(id);
        return R.success("删除热门活动成功！");
    }

    @PutMapping
    public R<String> update(@RequestBody HotActivity hotActivity){
        hotActivityService.updateById(hotActivity);
        return R.success("更新热门活动成功！");
    }

    @GetMapping("/list")
    public R<List<HotActivity>> list(){
        List<HotActivity> hotActivities = hotActivityService.list();
        return R.success(hotActivities);
    }

    //@GetMapping("/page")
    //public R<IPage> page(int start,int pageSize){
    //
    //}
}

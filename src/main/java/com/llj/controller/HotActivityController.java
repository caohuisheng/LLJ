package com.llj.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.llj.common.BaseContext;
import com.llj.common.R;
import com.llj.mapper.HotActivityDao;
import com.llj.model.pojo.ActivityUser;
import com.llj.model.pojo.HotActivity;
import com.llj.model.vo.HotActivityVO;
import com.llj.service.IActivityUserService;
import com.llj.service.IHotActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hot_activity")
public class HotActivityController {
    @Autowired
    private IHotActivityService hotActivityService;
    @Autowired
    private IActivityUserService activityUserService;

    @PostMapping
    public R<String> save(@RequestBody HotActivity hotActivity){
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

    /**
     * 分页条件查询
     * @param type
     * @param start
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<IPage<HotActivityVO>> queryByPageAndCondition(Integer type,int start,int pageSize){
        return R.success(hotActivityService.queryByPageAndCondition(type,start,pageSize));
    }

    /**
     * 根据id查询
     * @param activityId
     * @return
     */
    @GetMapping("{activityId}")
    public R<HotActivityVO> page(@PathVariable Long activityId){
        HotActivityVO hotActivityVO = hotActivityService.queryById(activityId);
        return R.success(hotActivityVO);
    }

    @PostMapping("attend")
    public R<String> attend(Long activityId){
        ActivityUser activityUser = new ActivityUser();
        activityUser.setActivityId(activityId);
        activityUserService.save(activityUser);
        return R.success("加入活动成功！");
    }

    @PostMapping("quit")
    public R<String> quit(Long activityId){
        QueryWrapper<ActivityUser> qw = new QueryWrapper<ActivityUser>()
                .eq("activity_id", activityId)
                .eq("create_user", BaseContext.getCurrentId());
        activityUserService.remove(qw);
        return R.success("退出活动成功！");
    }
}

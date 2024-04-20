package com.llj.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.llj.common.CustomException;
import com.llj.common.R;
import com.llj.model.pojo.Demand;
import com.llj.model.pojo.User;
import com.llj.service.IDemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demand")
public class DemandController {

    @Autowired
    private IDemandService demandService;

    /**
     * 保存需求
     * @param demand
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Demand demand){
        demandService.save(demand);
        return R.success("保存需求成功！");
    }

    /**
     * 更新需求
     */
    @PutMapping
    public R<String> update(@RequestBody Demand demand){
        demandService.updateById(demand);
        return R.success("更新需求成功！");
    }

    /**
     * 付款
     */
    @PostMapping("pay")
    public R<String> pay(Long demandId){
        Demand demand = demandService.getById(demandId);
        if(demand.getStatus() != 1){
            throw new CustomException("保存后才可以付款！");
        }
        demandService.pay(demandId);
        return R.success("付款成功！");
    }

    /**
     * 发布
     */
    @PostMapping("post")
    public R<String> post(Long demandId){
        Demand demand = demandService.getById(demandId);
        if(demand.getIsFree()==0&&demand.getStatus()!=1){
            throw new CustomException("保存后才可以发布！");
        }
        if(demand.getIsFree()==1&&demand.getStatus()!=2){
            throw new CustomException("付款后才可以发布！");
        }
        demandService.post(demandId);
        return R.success("发布成功！");
    }

    /**
     * 根据id查询
     */
    @GetMapping("{demandId}")
    public R<Demand> queryById(@PathVariable Long demandId){
        Demand demand = demandService.getById(demandId);
        return R.success(demand);
    }

    /**
     * 分页条件查询
     */
    @GetMapping("page")
    public R<IPage> queryByPageAndCondition(String keyword,int status,int start,int pageSize){
        IPage<Demand> page = new Page<>(start,pageSize);
        QueryWrapper<Demand> qw = new QueryWrapper<Demand>().eq("status",status).like("title",keyword).orderByDesc("update_time");
        demandService.page(page, qw);
        return R.success(page);
    }

    /**
     * 查询需求对应志愿者
     * @param demandId
     * @return
     */
    @GetMapping("volunteer")
    public R<User> queryVolunteer(Long demandId){
        User volunteer = demandService.queryVolunteer(demandId);
        return R.success(volunteer);
    }
}

package com.llj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.llj.common.BaseContext;
import com.llj.common.R;
import com.llj.model.pojo.Sign;
import com.llj.service.ISignLikerService;
import com.llj.service.ISignRemarkService;
import com.llj.service.ISignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sign")
public class SignController {
    @Autowired
    private ISignService signService;
    @Autowired
    private ISignLikerService signLikerService;
    @Autowired
    private ISignRemarkService signRemarkService;

    /**
     * 打卡
     */
    @PostMapping
    public R<String> save(@RequestBody Sign sign){
        signService.save(sign);
        return R.success("添加打卡成功！");
    }

    /**
     * 删除打卡
     */
    @DeleteMapping("{signId}")
    public R<String> delete(@PathVariable Long signId){
        signService.removeById(signId);
        return R.success("删除打卡成功！");
    }

    @PutMapping
    public R<String> update(@RequestBody Sign sign){
        signService.updateById(sign);
        return R.success("修改打卡成功！");
    }

    /**
     * 根据id查询打卡
     */
    @GetMapping("{signId}")
    public R<Sign> queryById(@PathVariable Long signId){
        Sign sign = signService.getById(signId);
        return R.success(sign);
    }

    /**
     * 分页查询打卡
     */
    @GetMapping("page")
    public R<IPage> queryByPageAndCondition(Integer type, int start, int pageSize){
        Page<Sign> page=new Page<>(start,pageSize);
        LambdaQueryWrapper<Sign> lqw = new LambdaQueryWrapper<>();
        lqw.eq(type!=null,Sign::getType,type).orderByDesc(Sign::getCreateTime);
        Page<Sign> result = signService.page(page);
        return R.success(result);
    }

    /**
     * 分页查询我的打卡
     */
    @GetMapping("page/my")
    public R<IPage> queryMyByPageAndCondition(Integer type, int start, int pageSize){
        Page<Sign> page=new Page<>(start,pageSize);
        LambdaQueryWrapper<Sign> lqw = new LambdaQueryWrapper<>();
        lqw.eq(type!=null,Sign::getType,type).eq(Sign::getCreateUser, BaseContext.getCurrentId()).orderByDesc(Sign::getCreateTime);
        Page<Sign> result = signService.page(page);
        return R.success(result);
    }

    /**
     * 点赞打卡
     */
    @PostMapping("like")
    public R<String> like(Long signId){
        Sign.Liker liker = new Sign.Liker();
        liker.setSignId(signId);
        signLikerService.save(liker);
        return R.success("点赞成功！");
    }

    /**
     * 取消点赞打卡
     */
    @PostMapping("unlike/{signId}")
    public R<String> unlike(@PathVariable Long signId){
        LambdaQueryWrapper<Sign.Liker> lqw=new LambdaQueryWrapper<>();
        lqw.eq(Sign.Liker::getSignId,signId).eq(Sign.Liker::getCreateUser, BaseContext.getCurrentId());
        signLikerService.remove(lqw);
        return R.success("取消点赞成功！");
    }

    /**
     * 添加打卡评论
     */
    @PostMapping("remark")
    public R<String> addRemark(@RequestBody Sign.Remark remark){
        signRemarkService.save(remark);
        return R.success("添加评论成功！");
    }

    /**
     * 删除打卡评论
     */
    @DeleteMapping("remark/{remarkId}")
    public R<String> removeRemark(@PathVariable Long remarkId){
        signRemarkService.removeById(remarkId);
        return R.success("删除评论成功！");
    }

    /**
     * 分页查询打卡对应的评论
     */
    @GetMapping("remark/page")
    public R<IPage> queryRemarkByPage(Long signId,int start,int pageSize){
        IPage<Sign.Remark> page=new Page<>(start,pageSize);
        LambdaQueryWrapper<Sign.Remark> lqw=new LambdaQueryWrapper<>();
        lqw.eq(Sign.Remark::getSignId,signId);
        IPage<Sign.Remark> result = signRemarkService.page(page, lqw);
        return R.success(result);
    }
}

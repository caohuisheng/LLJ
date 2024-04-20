package com.llj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llj.mapper.ActivityUserDao;
import com.llj.mapper.HotActivityDao;
import com.llj.model.pojo.ActivityUser;
import com.llj.model.pojo.HotActivity;
import com.llj.model.vo.HotActivityVO;
import com.llj.service.IHotActivityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotActivityServiceImpl extends ServiceImpl<HotActivityDao,HotActivity> implements IHotActivityService  {
    @Autowired
    private ActivityUserDao activityUserDao;

    private HotActivityVO getActivityVO(HotActivity hotActivity){
        HotActivityVO vo = new HotActivityVO();
        BeanUtils.copyProperties(hotActivity,vo);
        QueryWrapper<ActivityUser> qw = new QueryWrapper<ActivityUser>().eq("activity_id",hotActivity.getId());
        Integer total = activityUserDao.selectCount(qw);
        vo.setTotal(total);
        return vo;
    }

    @Override
    public HotActivityVO queryById(Long activityId) {
        return getActivityVO(super.getById(activityId));
    }

    @Override
    public IPage<HotActivityVO> queryByPageAndCondition(Integer type, int start, int pageSize) {
        //分页查询原始结果
        IPage<HotActivity> page = new Page<>(start,pageSize);
        QueryWrapper<HotActivity> qw = new QueryWrapper<HotActivity>().eq("type",type).orderByDesc("create_time");
        IPage<HotActivity> results = this.page(page, qw);
        //查询其它信息，并添加到新结果中
        IPage<HotActivityVO> resultsNew = new Page<>();
        BeanUtils.copyProperties(results,resultsNew);
        List<HotActivityVO> recordsNew = results.getRecords().stream().map(this::getActivityVO).collect(Collectors.toList());
        resultsNew.setRecords(recordsNew);
        return resultsNew;
    }
}

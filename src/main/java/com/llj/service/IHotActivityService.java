package com.llj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.llj.model.pojo.HotActivity;
import com.llj.model.vo.HotActivityVO;

public interface IHotActivityService extends IService<HotActivity> {
    HotActivityVO queryById(Long activityId);
    IPage<HotActivityVO> queryByPageAndCondition(Integer type,int start,int pageSize);
}

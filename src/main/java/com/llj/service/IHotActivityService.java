package com.llj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.llj.model.pojo.HotActivity;

public interface IHotActivityService extends IService<HotActivity> {
    IPage page(int start,int pageSize);
}

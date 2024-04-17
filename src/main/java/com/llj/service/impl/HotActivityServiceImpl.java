package com.llj.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llj.mapper.HotActivityDao;
import com.llj.model.pojo.HotActivity;
import com.llj.service.IHotActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotActivityServiceImpl extends ServiceImpl<HotActivityDao,HotActivity> implements IHotActivityService  {
    @Autowired
    private HotActivityDao hotActivityDao;

    @Override
    public IPage<HotActivity> page(int start, int pageSize) {
        IPage<HotActivity> page = new Page(start,pageSize);
        hotActivityDao.selectPage(page,null);
        return page;
    }
}

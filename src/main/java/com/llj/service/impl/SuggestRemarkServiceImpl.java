package com.llj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llj.mapper.SuggestRemarkDao;
import com.llj.model.pojo.SuggestRemark;
import com.llj.service.ISuggestRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class SuggestRemarkServiceImpl extends ServiceImpl<SuggestRemarkDao, SuggestRemark> implements ISuggestRemarkService {
    @Autowired
    public SuggestRemarkDao remarkDao;

    public void removeBySuggestId(Long suggestId) {
        //删除该内容id对应的评论
        LambdaQueryWrapper<SuggestRemark> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SuggestRemark::getSuggestId,suggestId);
        remarkDao.delete(lqw);
    }
}

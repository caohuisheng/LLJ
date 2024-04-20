package com.llj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llj.mapper.SignDao;
import com.llj.mapper.SignLikerDao;
import com.llj.mapper.SignRemarkDao;
import com.llj.model.pojo.Sign;
import com.llj.service.ISignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class SignServiceImpl extends ServiceImpl<SignDao, Sign> implements ISignService {
    @Autowired
    private SignRemarkDao signRemarkDao;
    @Autowired
    private SignLikerDao signLikerDao;

    /**
     * 根据id删除打卡，同时删除对应的评论和点赞
     * @param id
     * @return
     */
    @Override
    public boolean removeById(Serializable id) {
        super.removeById(id);
        //删除打卡的评论
        LambdaQueryWrapper<Sign.Remark> lqw1 = new LambdaQueryWrapper<>();
        lqw1.eq(Sign.Remark::getSignId,id);
        signRemarkDao.delete(lqw1);
        //删除打卡的点赞
        LambdaQueryWrapper<Sign.Liker> lqw2 = new LambdaQueryWrapper<>();
        lqw2.eq(Sign.Liker::getSignId,id);
        signLikerDao.delete(lqw2);
        return true;
    }
}

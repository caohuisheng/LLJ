package com.llj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llj.common.CustomException;
import com.llj.mapper.DemandDao;
import com.llj.mapper.OrderDao;
import com.llj.mapper.UserDao;
import com.llj.model.pojo.Demand;
import com.llj.model.pojo.Orders;
import com.llj.model.pojo.User;
import com.llj.service.IDemandService;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemandServiceImpl extends ServiceImpl<DemandDao, Demand> implements IDemandService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;

    @Override
    public void pay(Long demandId) {
        //todo: 支付操作
        //设置需求状态：已付款
        UpdateWrapper<Demand> uw = new UpdateWrapper<Demand>().eq("id",demandId).set("status",2);
        this.update(uw);
    }

    @Override
    public void post(Long demandId) {
        //设置需求状态：已发布
        UpdateWrapper<Demand> uw = new UpdateWrapper<Demand>().eq("id",demandId).set("status",3);
        this.update(uw);
        //创建订单
        Orders order = new Orders();
        order.setDemandId(demandId);
        orderDao.insert(order);
    }

    @Override
    public User queryVolunteer(Long demandId) {
        QueryWrapper<Orders> qw = new QueryWrapper<Orders>().eq("demand_id",demandId);
        Orders orders = orderDao.selectOne(qw);
        if(orders.getStatus() != 2){
            throw new CustomException("该订单尚未被志愿者确认！");
        }
        return userDao.selectById(orders.getServerId());
    }
}

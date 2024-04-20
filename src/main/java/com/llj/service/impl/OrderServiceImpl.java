package com.llj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llj.common.BaseContext;
import com.llj.mapper.DemandDao;
import com.llj.mapper.OrderDao;
import com.llj.model.dto.OrderDto;
import com.llj.model.pojo.Demand;
import com.llj.model.pojo.Orders;
import com.llj.service.IOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderDao, Orders> implements IOrderService {
    @Autowired
    private DemandDao demandDao;

    @Override
    public OrderDto getById(Long orderId) {
        //查询订单
        Orders order = super.getById(orderId);
        return getOrderDto(order);
    }

    private OrderDto getOrderDto(Orders order){
        //创建OrderDto
        OrderDto dto = new OrderDto();
        BeanUtils.copyProperties(order,dto);
        //查询订单对应需求信息
        Demand demand = demandDao.selectById(order.getDemandId());
        dto.setTitle(demand.getTitle());
        dto.setDetails(demand.getDetails());
        dto.setPictures(demand.getPictures());
        dto.setIsFree(demand.getIsFree());
        dto.setPrice(demand.getPrice());
        return dto;
    }

    @Override
    public IPage<OrderDto> queryByAndCondition(String keyword, Integer status, int start, int pageSize) {
        IPage<Orders> page = new Page<>(start,pageSize);
        QueryWrapper<Orders> qw = new QueryWrapper<Orders>()
                .eq("create_user", BaseContext.getCurrentId())
                .eq(status!=null,"status",status)
                .like(StringUtils.isNotBlank(keyword),"title",keyword)
                .orderByDesc("create_time");
        this.page(page,qw);
        IPage<OrderDto> pageNew = new Page<>();
        BeanUtils.copyProperties(page,pageNew);
        List<Orders> records = page.getRecords();
        List<OrderDto> recordsNew = records.stream().map(order -> getOrderDto(order)).collect(Collectors.toList());
        pageNew.setRecords(recordsNew);
        return pageNew;
    }
}

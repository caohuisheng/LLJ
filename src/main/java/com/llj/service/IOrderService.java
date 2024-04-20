package com.llj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.llj.model.dto.OrderDto;
import com.llj.model.pojo.Orders;

public interface IOrderService extends IService<Orders> {
    OrderDto getById(Long orderId);
    IPage<OrderDto> queryByAndCondition(String keyword,Integer status,int start,int pageSize);
}

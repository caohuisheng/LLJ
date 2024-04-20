package com.llj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llj.mapper.OrderRemarkDao;
import com.llj.model.pojo.Orders;
import com.llj.service.IOrderRemarkService;
import org.springframework.stereotype.Service;

@Service
public class OrderRemarkServiceImpl extends ServiceImpl<OrderRemarkDao, Orders.Remark> implements IOrderRemarkService {
}

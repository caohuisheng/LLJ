package com.llj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.llj.model.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDao extends BaseMapper<Orders> {
}

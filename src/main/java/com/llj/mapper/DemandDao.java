package com.llj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.llj.model.pojo.Demand;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DemandDao extends BaseMapper<Demand> {
}

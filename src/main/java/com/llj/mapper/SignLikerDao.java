package com.llj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.llj.model.pojo.Sign;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignLikerDao extends BaseMapper<Sign.Liker> {
}

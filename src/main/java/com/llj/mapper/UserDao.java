package com.llj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.llj.model.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {

}

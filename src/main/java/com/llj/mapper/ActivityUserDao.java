package com.llj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.llj.model.pojo.ActivityUser;
import com.llj.model.pojo.HotActivity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityUserDao extends BaseMapper<ActivityUser> {
}

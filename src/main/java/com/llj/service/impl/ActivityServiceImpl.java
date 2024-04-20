package com.llj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llj.mapper.ActivityUserDao;
import com.llj.model.pojo.ActivityUser;
import com.llj.service.IActivityUserService;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityUserDao, ActivityUser> implements IActivityUserService {
}

package com.llj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.llj.model.dto.LoginDto;
import com.llj.model.dto.RegisterDto;
import com.llj.model.pojo.User;

public interface IUserService extends IService<User> {
    User register(RegisterDto params);
    User login(LoginDto params);
}

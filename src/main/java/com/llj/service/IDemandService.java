package com.llj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.llj.model.pojo.Demand;
import com.llj.model.pojo.User;

public interface IDemandService extends IService<Demand> {
    void pay(Long demandId);
    void post(Long demandId);
    User queryVolunteer(Long demandId);
}

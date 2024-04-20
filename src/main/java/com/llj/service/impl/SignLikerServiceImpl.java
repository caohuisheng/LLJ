package com.llj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llj.mapper.SignLikerDao;
import com.llj.mapper.SignRemarkDao;
import com.llj.model.pojo.Sign;
import com.llj.service.ISignLikerService;
import com.llj.service.ISignRemarkService;
import org.springframework.stereotype.Service;

@Service
public class SignLikerServiceImpl extends ServiceImpl<SignLikerDao, Sign.Liker> implements ISignLikerService {

}

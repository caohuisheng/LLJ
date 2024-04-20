package com.llj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llj.mapper.SignDao;
import com.llj.mapper.SignRemarkDao;
import com.llj.model.pojo.Sign;
import com.llj.service.ISignRemarkService;
import com.llj.service.ISignService;
import org.springframework.stereotype.Service;

@Service
public class SignRemarkServiceImpl extends ServiceImpl<SignRemarkDao, Sign.Remark> implements ISignRemarkService {

}

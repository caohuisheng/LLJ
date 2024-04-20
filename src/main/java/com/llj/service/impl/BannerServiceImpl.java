package com.llj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llj.mapper.BannerDao;
import com.llj.model.pojo.Banner;
import com.llj.service.IBannerService;
import org.springframework.stereotype.Service;

@Service
public class BannerServiceImpl extends ServiceImpl<BannerDao, Banner> implements IBannerService {
}

package com.llj.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.llj.common.R;
import com.llj.model.dto.SuggestDto;
import com.llj.model.pojo.Suggest;
import org.springframework.stereotype.Service;

public interface ISuggestService extends IService<Suggest> {
    public R<IPage> queryByPageAndCondition(int current, int pageSize, String title, Integer type);
}

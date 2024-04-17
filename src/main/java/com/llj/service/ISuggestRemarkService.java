package com.llj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.llj.model.pojo.SuggestRemark;

public interface ISuggestRemarkService extends IService<SuggestRemark> {
    void removeBySuggestId(Long id);
}

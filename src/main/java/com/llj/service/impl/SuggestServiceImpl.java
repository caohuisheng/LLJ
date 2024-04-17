package com.llj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llj.common.R;
import com.llj.mapper.SuggestDao;
import com.llj.mapper.SuggestRemarkDao;
import com.llj.model.dto.SuggestDto;
import com.llj.model.pojo.Suggest;
import com.llj.model.pojo.SuggestRemark;
import com.llj.service.ISuggestRemarkService;
import com.llj.service.ISuggestService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuggestServiceImpl extends ServiceImpl<SuggestDao, Suggest> implements ISuggestService {
    @Autowired
    private ISuggestRemarkService remarkService;

    @Override
    public boolean removeById(Serializable id) {
        remarkService.removeBySuggestId((Long)id);
        return super.removeById(id);
    }

    /**
     * 条件查询并进行分页
     */
    public R<IPage> queryByPageAndCondition(int current, int pageSize, String title, Integer type){
        // 分页查询
        IPage<Suggest> page = new Page<>(current,pageSize);
        LambdaQueryWrapper<Suggest> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Suggest::getType,type);
        lqw.like(title!=null,Suggest::getTitle,title);
        lqw.orderByDesc(Suggest::getCreateTime);
        IPage<Suggest> pageInfo = this.page(page, lqw);
        //为每个suggest添加评论列表信息
        //IPage<SuggestDto> newPageInfo = new Page<>();
        //BeanUtils.copyProperties(pageInfo,newPageInfo,"records");
        //List<Suggest> records = pageInfo.getRecords();
        //List<SuggestDto> collect = records.stream().map(suggest -> {
        //    SuggestDto suggestDto = new SuggestDto();
        //    BeanUtils.copyProperties(suggest, suggestDto);
        //    LambdaQueryWrapper<SuggestRemark> lqw = new LambdaQueryWrapper<>();
        //    lqw.eq(SuggestRemark::getSuggestId, suggestDto.getId());
        //    List<SuggestRemark> remarkList = remarkDao.selectList(lqw);
        //    suggestDto.setRemarkList(remarkList);
        //    return suggestDto;
        //}).collect(Collectors.toList());
        //newPageInfo.setRecords(collect);

        return R.success(pageInfo);
    }
}

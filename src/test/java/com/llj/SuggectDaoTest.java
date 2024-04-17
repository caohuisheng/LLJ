package com.llj;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.llj.mapper.SuggestDao;
import com.llj.model.pojo.Suggest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class SuggectDaoTest {
    @Autowired
    private SuggestDao suggestMapper;

    @Test
    public void testInsert(){
        Suggest suggest = new Suggest();
        suggest.setTitle("毕业生指南");
        suggest.setContent("毕业生指南手册");
        suggest.setAuthor("大学生网");
        suggest.setType(0);
        suggest.setPicture("");
        suggestMapper.insert(suggest);
    }

    @Test
    public void testSelect(){
        List<Suggest> suggests = suggestMapper.selectList(null);
        System.out.println(suggests.toString());
    }

    @Test
    public void testSelectPage(){
        IPage<Suggest> page = new Page<>(0,2);
        suggestMapper.selectPage(page,null);
        System.out.println(page.getPages());
    }
}

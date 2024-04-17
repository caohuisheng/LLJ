package com.llj;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.llj.model.pojo.Suggest;
import com.llj.model.pojo.SuggestRemark;
import com.llj.service.ISuggestRemarkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SuggestRemarkServiceTest {

    @Autowired
    private ISuggestRemarkService suggestRemarkService;

    @Test
    void testAdd(){
        SuggestRemark suggestRemark = new SuggestRemark();
        suggestRemark.setSuggestId(1L);
        suggestRemark.setRemark("干货满满！");
        suggestRemarkService.save(suggestRemark);
    }

    @Test
    void testPage(){
        IPage page = new Page(0,5);
        IPage result = suggestRemarkService.page(page, null);
        System.out.println(result);
    }
}

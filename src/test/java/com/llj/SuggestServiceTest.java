package com.llj;

import com.llj.service.ISuggestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SuggestServiceTest {
    @Autowired
    private ISuggestService suggestService;

    @Test
    public void testSelect(){
        suggestService.list();
    }
}

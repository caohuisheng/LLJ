package com.llj;

import com.llj.model.pojo.StuQuestion;
import com.llj.service.IStuQuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StuQuestionTest {
    @Autowired
    IStuQuestionService stuQuestionService;

    @Test
    void testAdd(){
        StuQuestion stuQuestion = new StuQuestion();
        stuQuestion.setQues("abc");
        stuQuestion.setDetails("hello");
        stuQuestion.setTagList("aaa");
        stuQuestionService.save(stuQuestion);
    }
}

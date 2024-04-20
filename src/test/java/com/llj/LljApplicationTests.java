package com.llj;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class LljApplicationTests {

    public static void main(String[] args) throws Exception{
        long year = System.currentTimeMillis()/(1000L*3600*24*365);
        System.out.println(Long.MAX_VALUE/(1000L*3600*24*365));
    }

}

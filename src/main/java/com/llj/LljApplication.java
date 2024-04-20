package com.llj;

import com.llj.properties.JwtProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.bind.DefaultValue;

@SpringBootApplication
@Slf4j
public class LljApplication {


    public static void main(String[] args) {
        SpringApplication.run(LljApplication.class, args);
        log.info("项目启动成功...");
    }

}

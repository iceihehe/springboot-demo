package com.iceihehe.cm.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableScheduling
@ComponentScan("com.iceihehe")
@MapperScan("com.iceihehe.cm.dao.mapper")
public class CmWebApplication {

    public static void main(String[] args)  {
        SpringApplication.run(CmWebApplication.class, args);
    }

}

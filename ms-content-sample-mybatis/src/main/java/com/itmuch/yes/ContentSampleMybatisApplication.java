package com.itmuch.yes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author itmuch.com
 */
@SpringBootApplication
@MapperScan("com.itmuch.yes.repository")
public class ContentSampleMybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContentSampleMybatisApplication.class, args);
    }
}

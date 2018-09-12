package com.wey;

import java.io.UnsupportedEncodingException;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.wey.mybatis.mapper")
@ServletComponentScan
public class SpringBootMybatisSecurityApplication {
    
    /// http://www.mybatis.org/mybatis-3/zh/configuration.html#settings
    public static void main(String[] args) throws UnsupportedEncodingException {
        SpringApplication.run(SpringBootMybatisSecurityApplication.class, args);
    }
}

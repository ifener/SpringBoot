package com.wey;

import java.io.UnsupportedEncodingException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

@SpringBootApplication
public class SpringBootSecurityApplication {
    
    public static void main(String[] args) throws UnsupportedEncodingException {
        SpringApplication.run(SpringBootSecurityApplication.class, args);
        // System.out.println(URLDecoder.decode("中国人","GBK"));
    }
    
    @Bean
    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter() {
        return new OpenEntityManagerInViewFilter();
    }
}

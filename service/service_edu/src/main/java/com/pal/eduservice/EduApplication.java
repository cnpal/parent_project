package com.pal.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author pal
 * @Date Created in 2020/8/31 17:11
 * @Version: 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.pal"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}

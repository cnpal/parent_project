package com.pal.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author pal
 * @Date Created in 2020/10/21 16:16
 * @Version: 1.0
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) // 不操作数据库
//@EnableDiscoveryClient // nacos注册
@ComponentScan(basePackages = {"com.pal"})
public class VodApplication {
    public static void main(String[] args) {
        SpringApplication.run(VodApplication.class, args);
    }
}

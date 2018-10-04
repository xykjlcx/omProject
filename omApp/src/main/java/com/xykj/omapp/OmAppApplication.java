package com.xykj.omapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan(basePackages = {"com.xykj"})
//@EntityScan("com.xykj.omservice")
@EnableJpaRepositories("com.xykj.omservice")
@EnableAsync            // 开启异步任务
public class OmAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(OmAppApplication.class, args);
    }
}

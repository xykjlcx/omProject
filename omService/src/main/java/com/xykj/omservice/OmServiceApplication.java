package com.xykj.omservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.xykj"})
//@EntityScan("com.xykj.omservice")
@EnableJpaRepositories("com.xykj.omservice")
public class OmServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OmServiceApplication.class, args);
    }
}

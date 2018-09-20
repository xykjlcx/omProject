package com.xykj.omapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.MappedSuperclass;

@SpringBootApplication
@ComponentScan(basePackages = {"com.xykj"})
//@EntityScan("com.xykj.omservice")
@EnableJpaRepositories("com.xykj.omservice")
public class OmappApplication {

    public static void main(String[] args) {
        SpringApplication.run(OmappApplication.class, args);
    }
}

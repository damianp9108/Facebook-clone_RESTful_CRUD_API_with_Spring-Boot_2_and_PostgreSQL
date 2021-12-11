package com.example.facebookapi.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = {"com.example.facebookapi.business", "com.example.facebookapi.domain", "com.example.facebookapi.rest"})
@EntityScan(basePackages = "com.example.facebookapi.domain.entity")
@EnableJpaRepositories(basePackages = "com.example.facebookapi.domain.repository")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

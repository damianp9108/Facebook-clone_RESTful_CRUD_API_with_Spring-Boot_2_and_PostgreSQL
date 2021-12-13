package com.example.facebookapi.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// {"com.example.facebookapi.business", "com.example.facebookapi.domain", "com.example.facebookapi.rest"}
@SpringBootApplication
@ComponentScan(basePackages = "com.example.facebookapi")
@EntityScan(basePackages = "com.example.facebookapi")
@EnableJpaRepositories(basePackages = "com.example.facebookapi")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

package facebookapi.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "facebookapi")
@EntityScan(basePackages = "facebookapi")
@EnableJpaRepositories(basePackages = "facebookapi")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

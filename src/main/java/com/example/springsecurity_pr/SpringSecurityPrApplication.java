package com.example.springsecurity_pr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SpringSecurityPrApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityPrApplication.class, args);
    }

}

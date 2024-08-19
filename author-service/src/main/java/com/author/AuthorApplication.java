package com.author;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(
        basePackages = "com.client.author"
)
@SpringBootApplication
public class AuthorApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthorApplication.class,args);
    }
}

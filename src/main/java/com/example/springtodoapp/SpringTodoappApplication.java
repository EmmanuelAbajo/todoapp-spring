package com.example.springtodoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class SpringTodoappApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTodoappApplication.class, args);
      
    }
}

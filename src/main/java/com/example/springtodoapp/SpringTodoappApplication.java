package com.example.springtodoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;



@SpringBootApplication
@EnableEurekaClient
public class SpringTodoappApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTodoappApplication.class, args);
      
    }
}

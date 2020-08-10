package com.example.springtodoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.google.gson.Gson;

@SpringBootApplication
public class SpringTodoappApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTodoappApplication.class, args);
      
    }
    
    @Bean
    public Gson mapper() {
    	return new Gson();
    }

}

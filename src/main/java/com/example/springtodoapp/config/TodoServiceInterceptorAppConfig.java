package com.example.springtodoapp.config;

import com.example.springtodoapp.interceptor.TodoServiceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Component
public class TodoServiceInterceptorAppConfig implements WebMvcConfigurer {

    @Autowired
    private TodoServiceInterceptor todoServiceInterceptor;

    // Registering the interceptor with the interceptor registry
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(todoServiceInterceptor);
    }
}

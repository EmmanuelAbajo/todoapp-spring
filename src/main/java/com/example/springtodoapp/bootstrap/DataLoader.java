package com.example.springtodoapp.bootstrap;

import com.example.springtodoapp.dto.TodoRequestDTO;
import com.example.springtodoapp.service.TodoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(DataLoader.class);
    private final TodoService todoService;

    @Autowired
    public DataLoader(TodoService todoService) {
        this.todoService = todoService;
    }

    @Override
    public void run(String... args) throws Exception {
    	TodoRequestDTO todo1 = new TodoRequestDTO("dependency injection","Practice dependency injection using spring");
    	TodoRequestDTO todo2 = new TodoRequestDTO("dependency injection","Practice dependency injection using nodejs");

        todoService.createTodo(todo1);
        todoService.createTodo(todo2);

        log.info("Data loaded successfully");
    }
}

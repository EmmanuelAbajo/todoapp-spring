package com.example.springtodoapp.bootstrap;

import com.example.springtodoapp.entity.Todo;
import com.example.springtodoapp.service.TodoService;
import com.example.springtodoapp.service.impl.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(DataLoader.class);
    private final TodoService todoService;
    private final UserService userService;

    @Autowired
    public DataLoader(TodoService todoService, UserService userService) {
        this.todoService = todoService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        Todo todo1 = new Todo("dependency injection","Practice dependency injection using spring");
        Todo todo2 = new Todo("dependency injection","Practice dependency injection using nodejs");

        todoService.createTodo(todo1);
        todoService.createTodo(todo2);

        log.info("Data loaded successfully");
    }
}

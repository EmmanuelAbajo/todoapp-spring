package com.example.springtodoapp.controller;

import com.example.springtodoapp.entity.Todo;
import com.example.springtodoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/todo")
public class TodoController {


    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping(value = "/",method = RequestMethod.POST ,produces = MediaType.APPLICATION_JSON_VALUE,
                        consumes = MediaType.APPLICATION_JSON_VALUE)
    public Todo createTodo(@RequestBody Todo todo){
        return todoService.createTodo(todo);
    }

    @RequestMapping(value = "/",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Todo> getAllTodo() {
        return todoService.getAllTodo();
    }

    @RequestMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Optional<Todo> getTodoById(@PathVariable Integer id){
        return todoService.getTodoById(id);
    }

}

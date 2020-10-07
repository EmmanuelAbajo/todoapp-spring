package com.example.springtodoapp.controller;

import com.example.springtodoapp.entity.Todo;
import com.example.springtodoapp.service.TodoService;
import com.example.springtodoapp.service.impl.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/todo",produces = "application/json")
public class TodoController {

    private final TodoService todoService;
    private final UserService userService;

 // TODO: Add swagger documentation
    public TodoController(TodoService todoService,UserService userService) {
        this.todoService = todoService;
        this.userService = userService;
    }

    // TODO: Validate request body
    @PostMapping(value = "/")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo request){
    	request.setCreatedBy(userService.getLoggedinUserName());
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.createTodo(request));
    }

    // TODO: Validate that input is a long
    @GetMapping(value="/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id){
    	return ResponseEntity.ok().body(todoService.getTodoById(id));
    }
    

    @PutMapping(value = "/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo){
        return ResponseEntity.ok().body(todoService.updateTodo(id, todo));  
    }
    
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable Long id){
    	todoService.deleteTodoById(id);
    	// TODO: return notification of successful deletion
    	return ResponseEntity.noContent().build();
    }
    
    @GetMapping(value = "/")
    public ResponseEntity<List<Todo>> getAllTodoByUser() {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.getTodosByUser(userService.getLoggedinUserName()));
    }
    
    
    @GetMapping(value = "/all/")
    public ResponseEntity<List<Todo>> getAllTodo() {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.getAllTodo());
    }


    @DeleteMapping(value = "/all/")
    public ResponseEntity<Void> deleteAllTodo(){
    	todoService.deleteAllTodo();
    	// TODO: return notification of successful deletion
    	return ResponseEntity.noContent().build();
    }

}

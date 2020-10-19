package com.example.springtodoapp.controller;

import com.example.springtodoapp.entity.Todo;
import com.example.springtodoapp.service.TodoService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RefreshScope
@RequestMapping(value = "/todo",produces = "application/json")
@CrossOrigin(origins = "*")
public class TodoController {

    private final TodoService todoService;
    
    @Value("${sample.config.key}")
    private String key;
    

 // TODO: Add swagger documentation
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    
    @GetMapping(value="/key")
    public String getKey() {
    	return key;
    }

    // TODO: Validate request body
    @PostMapping(value = "/")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo request){
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.createTodo(request));
    }

    // TODO: Validate that input is a long
    @GetMapping(value="/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id){
    	return ResponseEntity.ok().body(todoService.getTodoById(id));
    }
    

    @PutMapping(value = "/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @Valid @RequestBody Todo todo){
        return ResponseEntity.ok().body(todoService.updateTodo(id, todo));  
    }
    
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable Long id){
    	todoService.deleteTodoById(id);
    	// TODO: return notification of successful deletion
    	return ResponseEntity.noContent().build();
    }
    
    
    @GetMapping(value = "/")
    public ResponseEntity<List<Todo>> getAllTodo() {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.getAllTodo());
    }


    @DeleteMapping(value = "/")
    public ResponseEntity<Void> deleteAllTodo(){
    	todoService.deleteAllTodo();
    	// TODO: return notification of successful deletion
    	return ResponseEntity.noContent().build();
    }

}

package com.example.springtodoapp.controller;

import com.example.springtodoapp.dto.TodoRequestDTO;
import com.example.springtodoapp.dto.TodoResponseDTO;
import com.example.springtodoapp.service.TodoService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RefreshScope
@RequestMapping(value = "/api/v1/todo",produces = "application/json")
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


    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<TodoResponseDTO> createTodo(@RequestBody @Valid TodoRequestDTO request){
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.createTodo(request));
    }
    
    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<TodoResponseDTO>> getAllTodo() {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.getAllTodo());
    }

    @GetMapping(value="/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<TodoResponseDTO> getTodoById(@PathVariable Long id){
    	return ResponseEntity.ok().body(todoService.getTodoById(id));
    }
    
    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<TodoResponseDTO> updateTodo(@PathVariable Long id, @Valid @RequestBody TodoRequestDTO todo){
        return ResponseEntity.ok().body(todoService.updateTodo(id, todo));  
    }
    
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable Long id){
    	todoService.deleteTodoById(id);
    	return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllTodo(){
    	todoService.deleteAllTodo();
    	return ResponseEntity.noContent().build();
    }

}

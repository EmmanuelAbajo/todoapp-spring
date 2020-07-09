package com.example.springtodoapp.controller;

import com.example.springtodoapp.entity.Todo;
import com.example.springtodoapp.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/todo",produces = "application/json")
@CrossOrigin(origins = "*")
public class TodoController {

    private final TodoService todoService;

 // TODO: Add swagger documentation
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // TODO: Validate request body
    @PostMapping(value = "/")
    public ResponseEntity<Todo> createTodo(RequestEntity<Todo> request){
    	URI location = URI.create(request.getUrl().toString() + "/");
        return ResponseEntity.created(location).body(todoService.createTodo(request.getBody()));
    }

    // TODO: Validate that input is a long
    @GetMapping(value="/{id}")
    public ResponseEntity<Object> getTodoById(@PathVariable Long id){
    	return ResponseEntity.ok().body(todoService.getTodoById(id));
    }
    

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateTodo(@PathVariable Long id, @Valid @RequestBody Todo todo){
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
        List<Todo> result = todoService.getAllTodo();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    @DeleteMapping(value = "/")
    public ResponseEntity<Void> deleteAllTodo(){
    	todoService.deleteAllTodo();
    	// TODO: return notification of successful deletion
    	return ResponseEntity.noContent().build();
    }

}

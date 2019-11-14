package com.example.springtodoapp.controller;

import com.example.springtodoapp.entity.Todo;
import com.example.springtodoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/todo")
@CrossOrigin(origins = "http://localhost:4200")
public class TodoController {

    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createTodo(@RequestBody Todo todo){
        Todo newTodo = todoService.createTodo(todo);

        Map<String,String> msg = new HashMap<>();
        msg.put("status","ok");
        msg.put("message","Todo id "+newTodo.getId()+" created");

        return ResponseEntity.ok().body(msg);
    }

    @GetMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllTodo() {
        Iterable<Todo> result = todoService.getAllTodo();

        if (todoService.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(todoService.isEmptyMsg());
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTodoById(@PathVariable Integer id){
        if (todoService.existsById(id)) {
            Todo todo = todoService.getTodoById(id);
            return ResponseEntity.ok().body(todo);
        }
        return ResponseEntity.status(HttpStatus.OK).body(todoService.isEmptyMsg());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteTodoById(@PathVariable Integer id){
        if (todoService.existsById(id)){
            todoService.deleteTodoById(id);
            return getAllTodo();
        }
        return ResponseEntity.status(HttpStatus.OK).body(todoService.isEmptyMsg());

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateTodo(@PathVariable Integer id, @RequestBody Todo todo){
        if (todoService.existsById(id)) {
            return ResponseEntity.ok().body(todoService.updateTodo(id, todo));
        }
        return ResponseEntity.status(HttpStatus.OK).body(todoService.isEmptyMsg());
    }

    @DeleteMapping(value = "/")
    public ResponseEntity<Object> deleteAllTodo(){
        if (todoService.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(todoService.isEmptyMsg());
        }
        todoService.deleteAllTodo();
        Map<String,String> msg = new HashMap<>();
        msg.put("status","ok");
        msg.put("message","All todos deleted!");
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }

}

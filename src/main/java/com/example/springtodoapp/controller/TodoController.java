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
    public ResponseEntity<Iterable<Todo>> getAllTodo() {
        Iterable<Todo> result = todoService.getAllTodo();

        if (result == null){
            Map<String,String> msg = new HashMap<>();
            msg.put("status","ok");
            msg.put("message","No todo found");
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Todo> getTodoById(@PathVariable Integer id){
        Todo todo = todoService.getTodoById(id);

        if (todo == null){
            Map<String,String> msg = new HashMap<>();
            msg.put("status","ok");
            msg.put("message","No todo found");
            return ResponseEntity.status(HttpStatus.OK).body(todo);
        }

        return ResponseEntity.ok().body(todo);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTodoById(@PathVariable Integer id){
        todoService.deleteTodoById(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Integer id, @RequestBody Todo todo){
        return ResponseEntity.ok().body(todoService.updateTodo(id, todo));
    }

    @DeleteMapping(value = "/")
    public void deleteAllTodo(){
        todoService.deleteAllTodo();
    }

}

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
import java.util.TreeMap;


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
    public ResponseEntity<Object> createTodo(@RequestBody Todo todo){
        Todo newTodo = todoService.createTodo(todo);

        Map<String,String> msg = new HashMap<>();
        msg.put("status","ok");
        msg.put("message","Todo id "+newTodo.getId()+" created");

        return ResponseEntity.ok().body(msg);
    }

    @RequestMapping(value = "/",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Todo>> getAllTodo() {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.getAllTodo());
    }

    @RequestMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Todo> getTodoById(@PathVariable Integer id){
        return ResponseEntity.ok().body(todoService.getTodoById(id));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void deleteTodoById(@PathVariable Integer id){
        todoService.deleteTodoById(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Todo> updateTodo(@PathVariable Integer id, @RequestBody Todo todo){
        return ResponseEntity.ok().body(todoService.updateTodo(id, todo));
    }

    @RequestMapping(value = "/",method = RequestMethod.DELETE)
    public void deleteAllTodo(){
        todoService.deleteAllTodo();
    }

}

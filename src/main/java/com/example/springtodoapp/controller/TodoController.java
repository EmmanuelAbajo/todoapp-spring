package com.example.springtodoapp.controller;

import com.example.springtodoapp.entity.Todo;
import com.example.springtodoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


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
    public String createTodo(@RequestBody Todo todo){
        Todo newTodo = todoService.createTodo(todo);
        return "Todo id "+newTodo.getId()+" created";
    }

    @RequestMapping(value = "/",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Todo> getAllTodo() {
        return todoService.getAllTodo();
    }

    @RequestMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Todo getTodoById(@PathVariable Integer id){
        return todoService.getTodoById(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void deleteTodoById(@PathVariable Integer id){
        todoService.deleteTodoById(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Todo updateTodo(@PathVariable Integer id,@RequestBody Todo todo){
        return todoService.updateTodo(id, todo);
    }

    @RequestMapping(value = "/",method = RequestMethod.DELETE)
    public void deleteAllTodo(){
        todoService.deleteAllTodo();
    }

}

package com.example.springtodoapp.service;

import com.example.springtodoapp.entity.Todo;
import com.example.springtodoapp.repository.TodoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TodoService {

    private TodoInterface todoInterface;

    @Autowired
    public TodoService(TodoInterface todoInterface) {
        this.todoInterface = todoInterface;
    }

    public Iterable<Todo> getAllTodo() {
        return todoInterface.findAll();
    }

    public Optional<Todo> getTodoById(Integer id) {
        return todoInterface.findById(id);
    }

    public Todo createTodo(Todo todo){
        return todoInterface.save(todo);
    }

    public void deleteTodoById(Integer id){
        todoInterface.deleteById(id);
    }

    public void deleteAllTodo(){
        todoInterface.deleteAll();
    }
}

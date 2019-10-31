package com.example.springtodoapp.service;

import com.example.springtodoapp.entity.Todo;
import com.example.springtodoapp.repository.TodoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TodoService {

    @Autowired
    private TodoInterface todoInterface;

    public Iterable<Todo> getAllTodo() {
        return todoInterface.findAll();
    }

    public Todo getTodoById(Integer id) {
        return todoInterface.findById(id).get();
    }

    public Todo createTodo(Todo todo){
        return todoInterface.save(todo);
    }

    public Todo updateTodo(Integer id,Todo todo){
        Todo updateTodo = todoInterface.findById(id).get();
        updateTodo.setName(todo.getName());
        updateTodo.setContent(todo.getContent());
        return updateTodo;
    }

    public void deleteTodoById(Integer id){
        todoInterface.deleteById(id);
    }

    public void deleteAllTodo(){
        todoInterface.deleteAll();
    }

    public Boolean isEmpty(){
        if (todoInterface.count() == 0) {
            return true;
        }
        return false;
    }
}


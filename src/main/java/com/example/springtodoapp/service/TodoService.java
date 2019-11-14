package com.example.springtodoapp.service;

import com.example.springtodoapp.entity.Todo;
import com.example.springtodoapp.repository.TodoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
        return todoInterface.save(updateTodo);
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

    public Map<String,String> isEmptyMsg(){
        Map<String,String> msg = new HashMap<>();
        msg.put("status","ok");
        msg.put("message","No todo found");

        return msg;
    }

    public boolean existsById(Integer id){
        if (todoInterface.existsById(id)){
            return true;
        }
        return false;
    }
}


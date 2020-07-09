package com.example.springtodoapp.service;

import java.util.List;

import com.example.springtodoapp.entity.Todo;

public interface TodoService {
    List<Todo> getAllTodo();

    Todo getTodoById(Long id);

    Todo createTodo(Todo todo);

    Todo updateTodo(Long id, Todo todo);

    void deleteTodoById(Long id);

    void deleteAllTodo();

}

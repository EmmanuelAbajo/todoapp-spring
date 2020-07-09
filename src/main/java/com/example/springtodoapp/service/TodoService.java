package com.example.springtodoapp.service;

import com.example.springtodoapp.entity.Todo;

public interface TodoService {
    Iterable<Todo> getAllTodo();

    Todo getTodoById(Long id);

    Todo createTodo(Todo todo);

    Todo updateTodo(Long id, Todo todo);

    void deleteTodoById(Long id);

    void deleteAllTodo();

}

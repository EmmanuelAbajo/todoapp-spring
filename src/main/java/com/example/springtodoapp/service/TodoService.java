package com.example.springtodoapp.service;

import com.example.springtodoapp.entity.Todo;
import java.util.Map;

public interface TodoService {
    Iterable<Todo> getAllTodo();

    Todo getTodoById(Integer id);

    Todo createTodo(Todo todo);

    Todo updateTodo(Integer id, Todo todo);

    void deleteTodoById(Integer id);

    void deleteAllTodo();

    boolean existsById(Integer id);

    Map<String,String> isEmptyMsg();

    Boolean isEmpty();
}

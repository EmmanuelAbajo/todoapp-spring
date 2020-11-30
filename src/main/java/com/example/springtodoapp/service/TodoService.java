package com.example.springtodoapp.service;

import java.util.List;

import com.example.springtodoapp.dto.TodoRequestDTO;
import com.example.springtodoapp.dto.TodoResponseDTO;

public interface TodoService {
    List<TodoResponseDTO> getAllTodo();

    TodoResponseDTO getTodoById(Long id);

    TodoResponseDTO createTodo(TodoRequestDTO request);

    TodoResponseDTO updateTodo(Long id, TodoRequestDTO request);

    void deleteTodoById(Long id);

    void deleteAllTodo();

}

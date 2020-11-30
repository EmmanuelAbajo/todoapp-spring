package com.example.springtodoapp.assembler;

import org.springframework.stereotype.Component;

import com.example.springtodoapp.dto.TodoRequestDTO;
import com.example.springtodoapp.dto.TodoResponseDTO;
import com.example.springtodoapp.entity.Todo;

@Component
public class TodoAssembler {
	
	public TodoResponseDTO mapEntityToDTO(Todo todo) {
		TodoResponseDTO response = new TodoResponseDTO();
		response.setId(todo.getId());
		response.setName(todo.getName());
		response.setContent(todo.getContent());
		return response;
	}
	
	public Todo mapDTOToEntity(TodoRequestDTO request) {
		Todo todo = new Todo();
		todo.setName(request.getName());
		todo.setContent(request.getContent());
		return todo;
	}
	
	

}

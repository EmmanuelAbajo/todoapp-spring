package com.example.springtodoapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.springtodoapp.entity.Todo;
import com.example.springtodoapp.repository.TodoRepository;
import com.example.springtodoapp.service.TodoService;

@SpringBootTest
public class TodoServiceTest {
	
	@Autowired
	private TodoService todoService;
	
	@MockBean
	private TodoRepository todoRepository;
	
	Optional<Todo> todo;
	
	@BeforeEach
	public void setUp() {
		todo = Optional.of(new Todo("Call tolu","This is to happen next friday"));
		todo.get().setId(1L);
		Mockito.when(todoRepository.findById(todo.get().getId()))
			.thenReturn(todo);
	}
		
	@Test
	public void getTodoById() throws Exception {
		assertEquals(todo.get(),todoService.getTodoById(todo.get().getId()));
	}
	
	@Test
	public void getAllTodo() throws Exception {
		List<Todo> todos = todoService.getAllTodo();
		assertEquals(todos.size(),0);
	}
	
	

}

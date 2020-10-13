package com.example.springtodoapp.unittests;

import static org.assertj.core.api.Assertions.assertThat;
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
		todo = Optional.of(new Todo("",""));
		Mockito.when(todoRepository.findById(1L))
			.thenReturn(todo);
	}
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(todoService).isNotNull();
	}
		
	@Test
	public void getTodoById() throws Exception {
		assertNotNull(todoService.getTodoById(1L));
		assertEquals(todo.get(),todoService.getTodoById(1L));
	}
	
	@Test
	public void getAllTodo() throws Exception {
		List<Todo> todos = todoService.getAllTodo();
		assertEquals(todos.size(),0);
	}
	
	

}

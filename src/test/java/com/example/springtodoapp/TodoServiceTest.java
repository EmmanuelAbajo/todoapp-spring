package com.example.springtodoapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Test;
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;

import com.example.springtodoapp.entity.Todo;
import com.example.springtodoapp.repository.TodoRepository;
import com.example.springtodoapp.service.TodoService;

//@RunWith(SpringRunner.class)
public class TodoServiceTest {
	
	@Autowired
	private TodoService todoService;
	
	@MockBean
	private TodoRepository todoRepository;
	
//	@Before
//	public void setUp() {
//	
//	}
		
		
	@Test
	public void getTodoById() throws Exception {
		Optional<Todo> todo = Optional.of(new Todo("Call tolu","This is to happen next friday"));
		Mockito.when(todoRepository.findById(todo.get().getId()))
			.thenReturn(todo);
		assertEquals(todoService.getTodoById(todo.get().getId()),todo);
	}
	
	

}

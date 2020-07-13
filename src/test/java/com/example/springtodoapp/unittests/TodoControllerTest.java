package com.example.springtodoapp.unittests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.example.springtodoapp.controller.TodoController;
import com.example.springtodoapp.entity.Todo;
import com.example.springtodoapp.service.TodoService;

@WebMvcTest(TodoController.class)
public class TodoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TodoService todoService;
	
	@Autowired
	private TodoController todoController;
	
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(todoController).isNotNull();
	}
	
	@Test
	public void getById() throws Exception {
		Todo todo = new Todo("","");
		todo.setId(1L);
		Mockito.when(todoService.getTodoById(todo.getId()))
			.thenReturn(todo);
		this.mockMvc.perform(get("/todo/1"))
			.andDo(print())
			.andExpect(status().isOk());
	}
	
	@Test
	public void getAll() throws Exception {
		List<Todo> todos = new ArrayList<>();
		Todo todo = new Todo("","");
		todos.add(todo);
		Mockito.when(todoService.getAllTodo()).thenReturn(todos);
		this.mockMvc.perform(get("/todo/"))
			.andDo(print())
			.andExpect(status().isOk());
				
	}
	
	@Test
	public void testCreateTodo() {
		Todo todo = new Todo("","");
		ResponseEntity<Todo> res = todoController.createTodo(todo);
		assertThat(res.getStatusCodeValue()).isEqualTo(201);
	}

}

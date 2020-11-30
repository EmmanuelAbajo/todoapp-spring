package com.example.springtodoapp.unittests;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.springtodoapp.controller.TodoController;
import com.example.springtodoapp.dto.TodoResponseDTO;
import com.example.springtodoapp.entity.Todo;
import com.example.springtodoapp.service.TodoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(TodoController.class)
public class TodoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TodoService todoService;

	@Test
	public void getById() throws Exception {
		Mockito.when(todoService.getTodoById(1L)).thenReturn(new TodoResponseDTO("code review", "do code review"));
		this.mockMvc.perform(get("/todo/1")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void getAll() throws Exception {
		Mockito.when(todoService.getAllTodo()).thenReturn(Arrays.asList(new TodoResponseDTO("code review", "do code review")));
		this.mockMvc.perform(get("/todo/")).andDo(print()).andExpect(status().isOk());

	}

	@Test
	public void testCreateTodo() throws Exception {
		this.mockMvc
				.perform(post("/todo/").contentType(MediaType.APPLICATION_JSON).content(asJsonString(new Todo("", ""))))
				.andDo(print()).andExpect(status().is2xxSuccessful());
	}

	static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}

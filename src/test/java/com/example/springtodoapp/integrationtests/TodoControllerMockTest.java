package com.example.springtodoapp.integrationtests;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.springtodoapp.entity.Todo;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerMockTest {

	@Autowired
	private MockMvc mockMvc;

	Todo request = new Todo("Code review", "Review code for alpha project");

	@Test
	public void testCreateTodoWithMockMvc() throws Exception {
		this.mockMvc.perform(post("/todo/").contentType(MediaType.APPLICATION_JSON).content(asJsonString(request)))
				.andDo(print()).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testGetTodoWithMockMvc() throws Exception {
		this.mockMvc.perform(get("/todo/1")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("dependency injection")));
	}
	
	@Test
	public void testGetAllTodoWithMockMvc() throws Exception {
		this.mockMvc.perform(get("/todo/")).andDo(print()).andExpect(status().isOk());
	}

	static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}

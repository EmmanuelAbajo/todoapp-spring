package com.example.springtodoapp.integrationtests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerIntegration {
	
	@Autowired
    private MockMvc mvc;
	
	
	@Test
	public void getAllTodosFromDb() throws Exception {
		this.mvc.perform(get("/todos/"))
			.andDo(print())
			.andExpect(status().is2xxSuccessful()); 
	}
	
	@Test
	public void getTodoByIdFromDb() throws Exception {
		this.mvc.perform(get("/todos/1"))
			.andDo(print())
			 .andExpect(status().isOk());
			 
	}

}

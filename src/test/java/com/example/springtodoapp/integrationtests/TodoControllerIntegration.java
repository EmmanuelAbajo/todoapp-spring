package com.example.springtodoapp.integrationtests;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TodoControllerIntegration {
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void getAllTodosFromDb() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/todos/",
				String.class)).isNotNull();
	}
	
	@Test
	public void getTodoByIdFromDb() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/todos/1",
				String.class)).isNotNull();
			 
	}

}

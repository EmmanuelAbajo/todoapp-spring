package com.example.springtodoapp.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.springtodoapp.entity.Todo;

public class TodoTest {
	
	private static Todo todo;
	
	@BeforeAll
	public static void setUp() {
		todo = new Todo();
	}
	
	@Test
	public void getId() {
		Long id = 4L;
		todo.setId(id);
		assertEquals(id,todo.getId());
	}

	@Test
	public void getName() {
		String name = "Emmanuel";
		todo.setName(name);
		assertEquals(name,todo.getName());
	}
	
	@Test
	public void getContent() {
		String content = "Hello world";
		todo.setContent(content);
		assertNotNull(todo.getContent());
	}
	
	@Test
	public void getUser() {
		assertNull(todo.getUser());
	}
}

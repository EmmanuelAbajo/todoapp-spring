package com.example.springtodoapp.unittests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.springtodoapp.entity.Todo;
import com.example.springtodoapp.repository.TodoRepository;

@DataJpaTest
public class TodoRepositoryTest {
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private TodoRepository todoRepository;
	
	@Test
	public void AllTodoCheck() {
		Todo todo = new Todo("","");
		entityManager.persist(todo);
		entityManager.flush();
		assertThat(todoRepository.findAll()).isNotNull();
	}

}

package com.example.springtodoapp.bootstrap;

import com.example.springtodoapp.dto.TodoRequestDTO;
import com.example.springtodoapp.entity.Role;
import com.example.springtodoapp.repository.RoleRepository;
import com.example.springtodoapp.service.TodoService;
import com.example.springtodoapp.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
    private final TodoService todoService;
    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public DataLoader(TodoService todoService, UserService userService, RoleRepository roleRepository) {
        this.todoService = todoService;
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
    	roleRepository.save(new Role("ROLE_ADMIN", "Admin role"));
		roleRepository.save(new Role("ROLE_USER", "User role"));
    	TodoRequestDTO todo1 = new TodoRequestDTO("dependency injection","Practice dependency injection using spring");
    	TodoRequestDTO todo2 = new TodoRequestDTO("dependency injection","Practice dependency injection using nodejs");

        todoService.createTodo(todo1);
        todoService.createTodo(todo2);
        
        userService.createAdmin("temmy", "password", "email@gmail.com");

        log.info("Data loaded successfully");
    }
}

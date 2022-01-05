package com.example.springtodoapp.service.impl;

import com.example.springtodoapp.assembler.TodoAssembler;
import com.example.springtodoapp.dto.TodoRequestDTO;
import com.example.springtodoapp.dto.TodoResponseDTO;
import com.example.springtodoapp.entity.Todo;
import com.example.springtodoapp.exceptions.TodoNotFoundException;
import com.example.springtodoapp.repository.TodoRepository;
import com.example.springtodoapp.service.TodoService;
import com.example.springtodoapp.service.UserService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private final UserService userService;
	private final TodoRepository todoRepository;
	private final TodoAssembler todoAssembler;

	public TodoServiceImpl(TodoRepository todoRepository, TodoAssembler todoAssembler, UserService userService) {
		this.todoRepository = todoRepository;
		this.todoAssembler = todoAssembler;
		this.userService = userService;
	}

	@Override
	public TodoResponseDTO getTodoById(Long id) {
		log.info("Finding todo with id: {}", id.toString());
		return todoRepository.findByIdAndUserId(id, userService.getLoggedInUser().getId())
				.map(todo -> todoAssembler.mapEntityToDTO(todo))
				.orElseThrow(() -> new TodoNotFoundException(Todo.class, id));
	}

	@Override
	public TodoResponseDTO createTodo(TodoRequestDTO request) {
		TodoResponseDTO response = null;
		try {
			log.debug("Saving data: {}", request.toString());
			request.setUser(userService.getLoggedInUser());
			Todo todo = todoAssembler.mapDTOToEntity(request);
			response = todoAssembler.mapEntityToDTO(todoRepository.save(todo));
			return response;
		} catch (Exception ex) {
			if (ex instanceof DataIntegrityViolationException) {
//    			throw new ConflictException(todo.getId());
			}
//    		log.error(ex.getMessage(),ex);
			throw ex;
		}
	}

	@Override
	public TodoResponseDTO updateTodo(Long id, TodoRequestDTO request) {
		TodoResponseDTO response = null;
		try {
			Optional<Todo> todo = todoRepository.findByIdAndUserId(id, userService.getLoggedInUser().getId());
			if (todo.isPresent()) {
				log.debug("Updating data for: {}", todo.get().toString());
				todo.get().setName(request.getName());
				todo.get().setContent(request.getContent());
				todo.get().setUser(userService.getLoggedInUser());
				response = todoAssembler.mapEntityToDTO(todoRepository.save(todo.get()));
			}
		} catch (Exception ex) {
			throw ex;
		}

		return response;
//       .orElseThrow(()-> new TodoNotFoundException(Todo.class,id));
	}

	@Override
	public void deleteTodoById(Long id) {
		try {
			log.debug("Deleting todo with id: {}", id.toString());
			Optional<Todo> todo = todoRepository.findById(id);
			if (todo.isPresent()) {
				todoRepository.delete(todo.get());
				log.debug("Todo with id: {} deleted", id.toString());
			}

		} catch (Exception ex) {
//    		log.error(ex.getMessage(),ex);
			throw ex;
		}
	}

	@Override
	public List<TodoResponseDTO> getAllTodo() {
		List<TodoResponseDTO> todos = new ArrayList<>();
		try {
			log.debug("Getting all todos");
			Iterator<Todo> result = todoRepository.findByUserId(userService.getLoggedInUser().getId()).iterator();
			while (result.hasNext()) {
				Todo todo = result.next();
				todos.add(todoAssembler.mapEntityToDTO(todo));
			}
		} catch (Exception ex) {
//			log.error(ex.getMessage(),ex);
			throw ex;
		}
		return todos;
	}

	@Override
	public void deleteAllTodo() {
		try {
			log.debug("Deleting all todos");
			todoRepository.deleteAll();
		} catch (Exception ex) {
//    		log.error(ex.getMessage(),ex);
			throw ex;
		}
	}

}

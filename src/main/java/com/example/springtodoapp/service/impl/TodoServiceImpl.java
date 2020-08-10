package com.example.springtodoapp.service.impl;

import com.example.springtodoapp.entity.Todo;
import com.example.springtodoapp.exceptions.ConflictException;
import com.example.springtodoapp.exceptions.TodoNotFoundException;
import com.example.springtodoapp.repository.TodoRepository;
import com.example.springtodoapp.service.TodoService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


@Service
public class TodoServiceImpl implements TodoService {

	private static final Logger log = LoggerFactory.getLogger(TodoServiceImpl.class);
    private final TodoRepository todoRepository;
    private final Gson gson;
    
    public TodoServiceImpl(TodoRepository todoRepository, Gson gson) {
		this.todoRepository = todoRepository;
		this.gson = gson;
	}

	
    @Override
    public Todo getTodoById(Long id) {
    	log.info("Finding todo with id: {}",id.toString());
        return todoRepository.findById(id)
        		.orElseThrow(()-> new TodoNotFoundException(Todo.class,id));
    }

    
    @Override
    public Todo createTodo(Todo todo){
    	// TODO: If body is invalid, throw invalid body exception
    	try {
    		log.debug("Saving data: {}",gson.toJson(todo));
    		return todoRepository.save(todo);
    	}catch (Exception ex) {
    		if (ex instanceof DataIntegrityViolationException) {
    			throw new ConflictException(todo.getId());
    		}
//    		log.error(ex.getMessage(),ex);
    		throw ex;
    	}
    }

    
    @Override
    public Todo updateTodo(Long id, Todo todo){
    	// TODO: If body is invalid, throw invalid body exception
        return todoRepository.findById(id)
        		.map(item -> {
        			log.debug("Updating data for: {}",gson.toJson(item));
        			item.setName(todo.getName());
        			item.setContent(todo.getContent());
        			return this.createTodo(item);
        		})
        		.orElseThrow(()-> new TodoNotFoundException(Todo.class,id));
    }

    
    @Override
    public void deleteTodoById(Long id){
    	try {
    		log.debug("Deleting todo with id: {}",id.toString());
    		todoRepository.delete(this.getTodoById(id));
    		log.debug("Todo with id: {} deleted",id.toString());
    	}catch (Exception ex) {
//    		log.error(ex.getMessage(),ex);
    		throw ex;
    	}
    }

    
    @Override
    public List<Todo> getAllTodo() {
    	List<Todo> todos = new ArrayList<>();
		try {
			log.debug("Getting all todos");
			todoRepository.findAll().forEach(todos::add);;
		} catch (Exception ex) {
//			log.error(ex.getMessage(),ex);
			throw ex;
		} 
		return todos;
    }
    
    
    @Override
    public void deleteAllTodo(){
    	try {
    		log.debug("Deleting all todos");
    		todoRepository.deleteAll();
    	}catch (Exception ex) {
//    		log.error(ex.getMessage(),ex);
    		throw ex;
    	}
    }

}  

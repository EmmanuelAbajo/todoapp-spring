package com.example.springtodoapp.repository;

import com.example.springtodoapp.entity.Todo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface TodoRepository extends CrudRepository<Todo,Long> {
	
	List<Todo> findByUserName(String userName);

}

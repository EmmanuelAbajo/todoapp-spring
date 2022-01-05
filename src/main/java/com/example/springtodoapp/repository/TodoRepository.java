package com.example.springtodoapp.repository;

import com.example.springtodoapp.entity.Todo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


public interface TodoRepository extends CrudRepository<Todo,Long> {
	
	Iterable<Todo> findByUserId (Integer id);
	Optional<Todo> findByIdAndUserId(Long id, Integer userId);

}

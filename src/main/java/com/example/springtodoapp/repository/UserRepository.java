package com.example.springtodoapp.repository;

import com.example.springtodoapp.entity.User;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {

    Optional<User> findByUsername(String username);
}

package com.example.springtodoapp.repository;

import com.example.springtodoapp.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserInterface extends CrudRepository<User,Integer> {

}

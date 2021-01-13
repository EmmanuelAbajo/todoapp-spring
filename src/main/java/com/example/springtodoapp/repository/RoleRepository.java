package com.example.springtodoapp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.springtodoapp.entity.Role;

public interface RoleRepository extends CrudRepository<Role,Integer>{

	Optional<Role> findByRoleName(String rolename);
}

package com.example.springtodoapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springtodoapp.entity.Role;
import com.example.springtodoapp.entity.User;
import com.example.springtodoapp.repository.RoleRepository;
import com.example.springtodoapp.repository.UserRepository;
import com.example.springtodoapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public String getLoggedinUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}

		return principal.toString();
	}

	@Override
	public Optional<User> signin(String username, String password, String email) {
		if (!userRepository.findByUsername(username).isPresent()) {
			Optional<Role> role = roleRepository.findByRoleName("ROLE_USER");
			return Optional.of(userRepository
					.save(new User(username, passwordEncoder.encode(password), role.get(), email)));
		}
		return Optional.empty();
	}

	@Override
	public List<User> getAll() {
		List<User> list = new ArrayList<>(); 
		userRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}
	
	@Override
	public
	Optional<User> createAdmin(String username, String password, String email) {
		roleRepository.save(new Role("ROLE_ADMIN", "Admin role"));
		roleRepository.save(new Role("ROLE_USER", "User role"));
		if (!userRepository.findByUsername(username).isPresent()) {
			Optional<Role> role = roleRepository.findByRoleName("ROLE_ADMIN");
			return Optional.of(userRepository
					.save(new User(username, passwordEncoder.encode(password), role.get(), email)));
		}
		return Optional.empty();
	}
}

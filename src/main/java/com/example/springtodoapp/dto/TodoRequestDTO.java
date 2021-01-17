package com.example.springtodoapp.dto;

import javax.validation.constraints.NotNull;

import com.example.springtodoapp.entity.User;

public class TodoRequestDTO {

	@NotNull
	private String name;

	@NotNull
	private String content;

	private User user;

	public TodoRequestDTO() {
	}

	public TodoRequestDTO(String name, String content) {
		this.name = name;
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "TodoRequestDTO [name=" + name + ", content=" + content + ", user=" + user + "]";
	}

}

package com.example.springtodoapp.dto;

public class TodoRequestDTO {
	
    private String name;
    private String content;
    
    public TodoRequestDTO() {}
	
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

	@Override
	public String toString() {
		return "TodoRequestDTO [name=" + name + ", content=" + content + "]";
	}
	
	
    
}

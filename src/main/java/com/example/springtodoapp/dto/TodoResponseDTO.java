package com.example.springtodoapp.dto;

public class TodoResponseDTO {
	
    private Long id;
    private String name;
    private String content;
    
    public TodoResponseDTO() {}
    
	public TodoResponseDTO(String name, String content) {
		this.name = name;
		this.content = content;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
    
    

}

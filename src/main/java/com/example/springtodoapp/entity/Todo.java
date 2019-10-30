package com.example.springtodoapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "Todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "content",nullable = false)
    private String content;

    @ManyToOne
    private User user;

    public Todo(){};

    public Todo(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public Todo(String name, String content, User user) {
        this.name = name;
        this.content = content;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

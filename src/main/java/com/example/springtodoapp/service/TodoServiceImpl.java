package com.example.springtodoapp.service;

import com.example.springtodoapp.entity.Todo;
import com.example.springtodoapp.models.Response;
import com.example.springtodoapp.repository.TodoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Primary
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoInterface todoInterface;

    @Override
    public Iterable<Todo> getAllTodo() {
        return todoInterface.findAll();
    }

    @Override
    public Todo getTodoById(Integer id) {
        return todoInterface.findById(id).get();
    }

    @Override
    public Todo createTodo(Todo todo){
        return todoInterface.save(todo);
    }

    @Override
    public Todo updateTodo(Integer id, Todo todo){
        Todo updateTodo = todoInterface.findById(id).get();
        updateTodo.setName(todo.getName());
        updateTodo.setContent(todo.getContent());
        return todoInterface.save(updateTodo);
    }

    @Override
    public void deleteTodoById(Integer id){
        todoInterface.deleteById(id);
    }

    @Override
    public void deleteAllTodo(){
        todoInterface.deleteAll();
    }

    @Override
    public Boolean isEmpty(){
        if (todoInterface.count() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public Map<String,String> isEmptyMsg(){
        Map<String,String> msg = new HashMap<>();
        msg.put("status","404");
        msg.put("message","No todo found");

        return msg;
    }

    @Override
    public boolean existsById(Integer id){
        if (todoInterface.existsById(id)){
            return true;
        }
        return false;
    }

    public Response create(Todo todo){
        Response response = new Response();
        try{
            if (todo.getId() != null){
                Optional<Todo> todoItem = todoInterface.findById(todo.getId());
                if (!todoItem.isPresent()){
                    response.setResponseCode("99");
                    response.setResponseMessage("No todo found");
                    return response;
                }
                todoItem.get().setName(todo.getName());
                todoItem.get().setContent(todo.getContent());
            }
        }catch (Exception e){
            e.printStackTrace();
            response.setResponseCode("99");
            response.setResponseMessage(e.getMessage());
        }
        return response;
    }

    @Transactional
    public Response delete(Integer id){
        Response response = new Response();
        try {
            Optional<Todo> todoItem = todoInterface.findById(id);
            if (!todoItem.isPresent()){

            }
            todoInterface.deleteById(id);
            response.setResponseCode("00");
            response.setResponseMessage("Successful");
        }catch (Exception e){
            e.printStackTrace();
            response.setResponseCode("99");
            response.setResponseMessage(e.getMessage());
        }
        return response;
    }
}


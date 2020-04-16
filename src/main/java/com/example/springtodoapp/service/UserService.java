package com.example.springtodoapp.service;

import com.example.springtodoapp.entity.User;
import com.example.springtodoapp.repository.CrudService;
import com.example.springtodoapp.repository.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService implements CrudService {

    @Autowired
    private UserInterface userInterface;

    @Override
    public Map<String, String> isEmptyMsg() {
        Map<String,String> msg = new HashMap<>();
        msg.put("status","404");
        msg.put("message","No user found");

        return msg;
    }

    @Override
    public Boolean isEmpty() {
        if (userInterface.count() == 0) {
            return true;
        }
        return false;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userInterface.findByUsername(username);
//        if (user != null) {
//            return user;
//        }
//        throw new UsernameNotFoundException("User '" + username + "' not found");
//
//    }
}

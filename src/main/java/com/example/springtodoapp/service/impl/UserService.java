package com.example.springtodoapp.service.impl;

import com.example.springtodoapp.repository.CrudService;
import com.example.springtodoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService implements CrudService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Map<String, String> isEmptyMsg() {
        Map<String,String> msg = new HashMap<>();
        msg.put("status","404");
        msg.put("message","No user found");

        return msg;
    }

    @Override
    public Boolean isEmpty() {
        if (userRepository.count() == 0) {
            return true;
        }
        return false;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//        if (user != null) {
//            return user;
//        }
//        throw new UsernameNotFoundException("User '" + username + "' not found");
//
//    }
}

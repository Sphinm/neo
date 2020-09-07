package com.example.neo.controller;

import com.example.neo.model.User;
import com.example.neo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/insert")
    public User insertUser() {
        User user = new User(3, "jake", 29);
        userMapper.insert(user);
        return user;
    }

    @RequestMapping("/findAll")
    public List<User> findAll() {
        System.out.println("receive request");
        return  userMapper.findAll();
    }
}

package com.example.neo.service;

import com.example.neo.mapper.UserMapper;
import com.example.neo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserService {
    @Autowired
    UserMapper UserMapper;

    public User findByUserMobile(User user) {
        return UserMapper.findByUserMobile(user.getMobile());
    }

    public User findByUserId(String userId) {
        return UserMapper.findByUserId(userId);
    }
}

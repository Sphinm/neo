package com.example.neo.service;

import com.example.neo.entity.params.IRegister;
import com.example.neo.model.User;

public interface AuthService {
    void logout();

    User login(String login);

    User registerUser(IRegister register);

    User findByUserId(String userId);
}
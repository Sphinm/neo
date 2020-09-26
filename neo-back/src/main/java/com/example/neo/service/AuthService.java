package com.example.neo.service;

import com.example.neo.model.IChangePassword;
import com.example.neo.enums.ResponseCodeEnum;
import com.example.neo.entity.User;

public interface AuthService {
    void logout();

    User login(String login);

    ResponseCodeEnum changePwd(IChangePassword pwd, String userId);
}

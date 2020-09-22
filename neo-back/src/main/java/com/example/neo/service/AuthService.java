package com.example.neo.service;

import com.example.neo.entity.params.IChangePassword;
import com.example.neo.enums.ResponseCodeEnum;
import com.example.neo.model.User;

public interface AuthService {
    void logout();

    User login(String login);

    ResponseCodeEnum changePwd(IChangePassword pwd, String userId);
}

package com.example.neo.service;

import com.example.neo.model.IChangePassword;
import com.example.neo.enums.ResponseCodeEnum;
import com.example.neo.entity.User;
import com.example.neo.model.ILogin;
import com.example.neo.utils.ResponseBean;

public interface AuthService {

    ResponseBean newLogin(ILogin iLogin);

    void logout();

    User login(String login);

    ResponseCodeEnum changePwd(IChangePassword pwd, String userId);
}

package com.example.neo.service;

import com.example.neo.model.IChangePassword;
import com.example.neo.model.ILogin;
import com.example.neo.utils.ResponseBean;

public interface AuthService {

    ResponseBean newLogin(ILogin iLogin);

    void logout();

    ResponseBean changePwd(IChangePassword pwd);
}

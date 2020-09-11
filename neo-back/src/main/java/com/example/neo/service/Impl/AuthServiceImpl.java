package com.example.neo.service.Impl;

import com.example.neo.constant.Constants;
import com.example.neo.entity.params.IRegister;
import com.example.neo.mapper.UserMapper;
import com.example.neo.model.User;
import com.example.neo.service.AuthService;
import com.example.neo.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    UserMapper UserMapper;

    public User login(String mobile) {
        return UserMapper.findByUserMobile(mobile);
    }

    public void logout() {
        CookieUtils.clean(Constants.TOKEN_KEY);
    }

    public User findByUserId(String userId) {
        return UserMapper.findByUserId(userId);
    }

    // TODO: 暂未实现
    public User registerUser(IRegister register) {
        return UserMapper.findByUserMobile(register.mobile);
    }
}
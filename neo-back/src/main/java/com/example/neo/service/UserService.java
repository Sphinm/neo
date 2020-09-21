package com.example.neo.service;

import com.example.neo.model.UserInfo;

public interface UserService {
    /**
     * 根据 userId 获取用户信息
     */
    UserInfo fetchUserInfo(String userId);

    /**
     * 添加用户信息
     */
    void insertUserInfo(UserInfo userInfo);

    /**
     * 更新用户信息
     */
    UserInfo updateUserInfo(UserInfo userInfo);

}

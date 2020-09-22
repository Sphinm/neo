package com.example.neo.service;

import com.example.neo.entity.params.ICreateUser;
import com.example.neo.enums.UserTypeEnum;
import com.example.neo.model.User;
import com.example.neo.model.UserInfo;

public interface UserService {
    /**
     * 根据 userId 获取用户
     */
    User findByUserId(String userId);

    /**
     * 创建新用户
     */
    void createUser(ICreateUser user, UserTypeEnum userType);

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

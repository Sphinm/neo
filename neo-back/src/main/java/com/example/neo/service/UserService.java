package com.example.neo.service;

import com.example.neo.entity.CompanyInfo;
import com.example.neo.entity.User;
import com.example.neo.enums.UserTypeEnum;
import com.example.neo.model.IGetUser;

public interface UserService {
    /**
     * 根据 userId 获取用户
     */
    IGetUser findByUserId(String userId);

    /**
     * 创建新用户
     */
    void createUser(User user, UserTypeEnum userType);

    /**
     * 根据 userId 获取用户信息
     */
    CompanyInfo fetchUserInfo(String userId);

    /**
     * 添加用户信息
     */
    void insertUserInfo(CompanyInfo companyInfo);

    /**
     * 更新用户信息
     */
    void updateUserInfo(CompanyInfo companyInfo);

}

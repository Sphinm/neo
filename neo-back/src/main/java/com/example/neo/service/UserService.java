package com.example.neo.service;

import com.example.neo.enums.UserTypeEnum;
import com.example.neo.model.ICreateUser;
import com.example.neo.model.IGetUser;
import com.example.neo.mybatis.model.NoCompany;

public interface UserService {
    /**
     * 根据 userId 获取用户
     */
    IGetUser fetchUserInfo();

    /**
     * 创建新用户
     */
    void createUser(ICreateUser user, UserTypeEnum userType);

    /**
     * 添加用户公司信息
     */
    void insertUserInfo(NoCompany companyInfo, UserTypeEnum userType);

    /**
     * 更新用户公司信息
     */
    void updateUserInfo(NoCompany companyInfo);

}

package com.example.neo.service;

import com.example.neo.enums.UserTypeEnum;
import com.example.neo.model.ICreateUser;
import com.example.neo.model.IGetUser;
import com.example.neo.mybatis.model.NoCompany;

import java.util.List;

public interface UserService {
    /**
     * 根据 userId 获取用户
     */
    IGetUser fetchUserInfo();

    /**
     * 查询商户信息
     */
    List<ICreateUser> fetchMerchantInfo(UserTypeEnum userType);

    /**
     * 更新代理公司信息
     */
    void updateMerchantInfo(ICreateUser user, UserTypeEnum userType);

    /**
     * 删除代理公司信息
     * 这里是逻辑删除，仅把该代理商的 is_locked 字段更改
     */
    void deleteMerchantInfo(int id);

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

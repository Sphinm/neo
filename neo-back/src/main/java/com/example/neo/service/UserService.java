package com.example.neo.service;

import com.example.neo.enums.UserTypeEnum;
import com.example.neo.model.ICreateUser;
import com.example.neo.mybatis.model.NeoCompany;
import com.example.neo.utils.ResponseBean;

public interface UserService {
    /**
     * 根据 userId 获取用户
     */
    ResponseBean fetchUserInfo();

    /**
     * 查询商户信息
     */
    ResponseBean fetchMerchantInfo(UserTypeEnum userType);

    /**
     * 更新代理公司信息
     */
    ResponseBean updateMerchantInfo(ICreateUser user, UserTypeEnum userType);

    /**
     * 删除代理公司信息
     * 这里是逻辑删除，仅把该代理商的 is_locked 字段更改
     */
    ResponseBean deleteMerchantInfo(int id);

    /**
     * 创建新用户
     */
    ResponseBean createUser(ICreateUser user, UserTypeEnum userType);

    /**
     * 添加用户公司信息
     */
    ResponseBean insertUserInfo(NeoCompany companyInfo, UserTypeEnum userType);

    /**
     * 更新用户公司信息
     */
    ResponseBean updateUserInfo(NeoCompany companyInfo);

    /**
     * 查询员工
     */
    ResponseBean fetchEmployee(int pageNum,int pageSize);

    /**
     * delete user
     */
    ResponseBean deleteEmployee(int employeeId);

    /**
     * 数据查询
     */
    ResponseBean fetchDataQuery();

    /**
     * 通过 merchantId 查询底下公司列表
     */
    ResponseBean fetchCompanyListByMerchantId(int merchantId);

}

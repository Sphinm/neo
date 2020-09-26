package com.example.neo.service.Impl;

import com.example.neo.model.ICreateUser;
import com.example.neo.enums.UserTypeEnum;
import com.example.neo.mapper.UserInfoMapper;
import com.example.neo.mapper.UserMapper;
import com.example.neo.entity.CompanyInfo;
import com.example.neo.entity.Role;
import com.example.neo.entity.User;
import com.example.neo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    UserMapper userMapper;

    public User findByUserId(String userId) {
        User user = userMapper.findByUserId(userId);
        Role role = userMapper.findRoleByUserId(user.getRoleId());

        log.info("111 => {} {}", user, role);
        return user;
    }

    /**
     * 根据 userId 获取用户信息
     *
     */
    @Override
    public CompanyInfo fetchUserInfo(String userId) {
        return userInfoMapper.fetchUserInfo(userId);
    }


    /**
     * 创建代理商和公司用户
     * 也可以创建公司和员工
     * @param user 用户信息
     */
    public void createUser(ICreateUser user, UserTypeEnum userType) {
        Date date = new Date();
        User userDto = new User();
        CompanyInfo companyInfo = new CompanyInfo();

        // 创建用户表
        userDto.setUserName(user.getUserName());
        userDto.setMobile(user.getMobile());
        userDto.setPassword("123456");
//        userDto.setIs_locked(0);
//        userDto.setRole_id(userType.getId());
//        userDto.setRelated_id();
//        userDto.setCreator_id();
//        userDto.setUpdate_id();
        userDto.setCreatorDate(date);
        userDto.setCreatorDate(date);

        if (userType != UserTypeEnum.EMPLOYEE) {
            insertUserInfo(companyInfo);
        }

        try {
            userMapper.createUser(userDto);
        } catch (RuntimeException e) {
            throw new RuntimeException("用户创建失败");
        }
    }

    /**
     * 添加用户信息
     *
     */
    @Override
    public void insertUserInfo(CompanyInfo userInfo) {
//        CompanyInfo userDo = keepUserInfo(userInfo);
//        userDo.setUserId(Snowflake.INSTANCE.nextId());
//        userDo.setCreateTimestamp(System.currentTimeMillis());
//        log.info("userInfo ===> {}", userDo);
//        userInfoMapper.insertUserInfo(userDo);
    }

    /**
     * 更新用户信息
     *
     */
    @Override
    public void updateUserInfo(CompanyInfo companyInfo) {
//        CompanyInfo userDo = keepUserInfo(companyInfo);
//        userDo.setUserId(companyInfo.getUserId());
//        userDo.setCreateTimestamp(companyInfo.getCreateTimestamp());
//        userInfoMapper.updateUserInfo(userDo);
    }

    private CompanyInfo keepUserInfo(CompanyInfo companyInfo) {
        CompanyInfo userDo = new CompanyInfo();
//        userDo.setUserName(companyInfo.getUserName());
//        userDo.setCompany(companyInfo.getCompany());
//        userDo.setTaxNumber(companyInfo.getTaxNumber());
//        userDo.setCompanyMobile(companyInfo.getCompanyMobile());
//        userDo.setCompanyAddress(companyInfo.getCompanyAddress());
//        userDo.setFixedTelephone(companyInfo.getFixedTelephone());
//        userDo.setRate(companyInfo.getRate());
//        userDo.setIndustry(companyInfo.getIndustry());
//        userDo.setBank(companyInfo.getBank());
//        userDo.setBankAccount(companyInfo.getBankAccount());
//        userDo.setReceiverName(companyInfo.getReceiverName());
//        userDo.setReceiverMobile(companyInfo.getReceiverMobile());
//        userDo.setReceiverAddress(companyInfo.getReceiverAddress());
//        userDo.setUpdateTimestamp(System.currentTimeMillis());
        return userDo;
    }
}

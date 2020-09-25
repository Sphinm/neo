package com.example.neo.service.Impl;

import com.example.neo.entity.params.ICreateUser;
import com.example.neo.enums.UserTypeEnum;
import com.example.neo.mapper.UserInfoMapper;
import com.example.neo.mapper.UserMapper;
import com.example.neo.model.CompanyInfo;
import com.example.neo.model.User;
import com.example.neo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    UserMapper userMapper;

    public User findByUserId(String userId) {
        return userMapper.findByUserId(userId);
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
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        User userDto = new User();
        CompanyInfo companyInfo = new CompanyInfo();

        // 创建用户表
        userDto.setUserName(user.getUserName());
        userDto.setMobile(user.getMobile());
        userDto.setPassword("123456");
        userDto.setIs_locked(0);
        userDto.setRole_id(userType.getId());
//        userDto.setRelated_id();
//        userDto.setCreator_id();
//        userDto.setUpdate_id();
        userDto.setCreator_date(timestamp);
        userDto.setCreator_date(timestamp);

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
    public void insertUserInfo(CompanyInfo userinfo) {
//        CompanyInfo userDo = keepUserInfo(userinfo);
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

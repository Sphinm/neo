package com.example.neo.service.Impl;

import com.example.neo.entity.params.ICreateUser;
import com.example.neo.enums.UserStatusEnum;
import com.example.neo.enums.UserTypeEnum;
import com.example.neo.mapper.UserInfoMapper;
import com.example.neo.mapper.UserMapper;
import com.example.neo.model.User;
import com.example.neo.model.UserInfo;
import com.example.neo.service.UserService;
import com.example.neo.utils.Snowflake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * 创建代理商和公司用户
     * 也可以创建公司和员工
     * @param user
     */
    public void createUser(ICreateUser user, UserTypeEnum userType) {
        User userDto = new User();
        UserInfo userInfo = new UserInfo();

        userDto.setUserId(Snowflake.INSTANCE.nextId());
        userDto.setStatus(UserStatusEnum.ENABLED);
        userDto.setUserName(user.getUserName());
        userDto.setMobile(user.getMobile());
        userDto.setPassword("123456");
        userDto.setCreateTimestamp(System.currentTimeMillis());
        userDto.setUpdateTimestamp(System.currentTimeMillis());
        switch (userType) {
            case ADMIN:
                userDto.setRole(UserTypeEnum.ADMIN);
                break;
            case MERCHANT:
                userDto.setRole(UserTypeEnum.MERCHANT);
                break;
            case COMPANY:
                userDto.setRole(UserTypeEnum.COMPANY);
                break;
            case EMPLOYEE:
                userDto.setRole(UserTypeEnum.EMPLOYEE);
                break;
        }

        if (userType != UserTypeEnum.EMPLOYEE) {
            userInfo.setUserId(Snowflake.INSTANCE.nextId());
            userInfo.setUserName(user.getUserName());
            userInfo.setCompany(user.getCompany());
            userInfo.setTaxNumber(user.getTaxNumber());
            userInfo.setCompanyMobile(user.getCompanyMobile());
            userInfo.setCompanyAddress(user.getCompanyAddress());
            userInfo.setFixedTelephone(user.getFixedTelephone());
            userInfo.setRate(user.getRate());
            userInfo.setIndustry(user.getIndustry());
            userInfo.setBank(user.getBank());
            userInfo.setBankAccount(user.getBankAccount());
            userInfo.setReceiverName(user.getReceiverName());
            userInfo.setReceiverMobile(user.getReceiverMobile());
            userInfo.setReceiverAddress(user.getReceiverAddress());
        }

        try {
            userMapper.createUser(userDto);
            if (userType != UserTypeEnum.EMPLOYEE) {
                userInfoMapper.insertUserInfo(userInfo);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("用户创建失败");
        }
    }

    /**
     * 根据 userId 获取用户信息
     *
     * @param userId
     */
    @Override
    public UserInfo fetchUserInfo(String userId) {
        return userInfoMapper.fetchUserInfo(userId);
    }

    /**
     * 添加用户信息
     *
     * @param userInfo
     */
    @Override
    public void insertUserInfo(UserInfo userInfo) {
        userInfoMapper.insertUserInfo(userInfo);
    }

    /**
     * 更新用户信息
     *
     * @param userInfo
     */
    @Override
    public UserInfo updateUserInfo(UserInfo userInfo) {
        return userInfoMapper.updateUserInfo(userInfo);
    }
}

package com.example.neo.service.Impl;

import com.example.neo.entity.params.ICreateUser;
import com.example.neo.mapper.UserInfoMapper;
import com.example.neo.mapper.UserMapper;
import com.example.neo.model.User;
import com.example.neo.model.UserInfo;
import com.example.neo.service.UserService;
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

    // TODO: 暂未实现
    public void createUser(ICreateUser user) {

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

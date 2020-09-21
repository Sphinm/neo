package com.example.neo.mapper;

import com.example.neo.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserInfoMapper {
    UserInfo fetchUserInfo(@Param("userId")  String userId);

    void insertUserInfo(UserInfo userInfo);

    UserInfo updateUserInfo(UserInfo userInfo);
}

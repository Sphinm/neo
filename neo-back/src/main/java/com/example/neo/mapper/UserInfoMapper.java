package com.example.neo.mapper;

import com.example.neo.entity.CompanyInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserInfoMapper {
    CompanyInfo fetchUserInfo(@Param("userId")  String userId);

    void insertUserInfo(CompanyInfo companyInfo);

    void updateUserInfo(CompanyInfo companyInfo);
}

package com.example.neo.model;

import com.example.neo.mybatis.model.NeoUser;
import com.example.neo.mybatis.model.NoCompany;
import lombok.Data;

@Data
public class ICreateUser {
    // user
    NeoUser userInfo;

    // companyInfo
    NoCompany companyInfo;
}

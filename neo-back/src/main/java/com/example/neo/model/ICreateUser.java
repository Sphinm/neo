package com.example.neo.model;

import com.example.neo.mybatis.model.NeoCompany;
import com.example.neo.mybatis.model.NeoUser;
import lombok.Data;

@Data
public class ICreateUser {
    // user
    NeoUser userInfo;

    // companyInfo
    NeoCompany companyInfo;
}

package com.example.neo.model;

import com.example.neo.mybatis.model.NeoCompany;
import lombok.Data;

@Data
public class IGetUser {
    String userName;
    String mobile;
    String email;
    Integer isLocked;
    String roleName;
    String roleType;
    NeoCompany userInfo;
}

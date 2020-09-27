package com.example.neo.model;

import lombok.Data;

@Data
public class IGetUser {
    String userName;
    String mobile;
    String email;
    Integer isLocked;
    String roleName;
    String roleType;
    Object userInfo;
}

package com.example.neo.model;

import lombok.Data;

@Data
public class IGetUser {
    String userName;
    String mobile;
    String email;
    // 是否锁定最好前后端都拦截一下
    Integer isLocked;
    String roleName;
    String roleType;
}

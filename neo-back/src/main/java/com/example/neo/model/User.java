package com.example.neo.model;

import com.example.neo.enums.UserStatusEnum;
import com.example.neo.enums.UserTypeEnum;
import lombok.Data;

@Data
public class User {
    String id;
    long userId;
    String userName;
    String mobile;
    String password;
    UserTypeEnum role;
    UserStatusEnum status;
}

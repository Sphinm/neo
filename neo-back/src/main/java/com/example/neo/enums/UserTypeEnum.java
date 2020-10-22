package com.example.neo.enums;

public enum UserTypeEnum {
    ADMIN(0),//管理员
    MERCHANT(1),//代理商
    COMPANY(2),//公司
    EMPLOYEE(3);//员工

    int id;

    UserTypeEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

package com.example.neo.enums;

public enum UserTypeEnum {
    ADMIN(1),//管理员
    MERCHANT(2),//代理商
    COMPANY(3),//公司
    EMPLOYEE(4);//员工

    int id;

    UserTypeEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

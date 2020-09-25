package com.example.neo.enums;

public enum UserTypeEnum {
    ADMIN(0),
    MERCHANT(1),
    COMPANY(2),
    EMPLOYEE(3);

    int id;

    UserTypeEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

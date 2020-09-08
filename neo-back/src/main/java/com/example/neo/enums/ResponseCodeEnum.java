package com.example.neo.enums;

public enum ResponseCodeEnum {
    //system
    SUCCESS("SUCCESS"),
    SERVER_ERROR("����������"),
    PASSWORD_ERROR("��¼ʧ�ܣ��������"),
    USER_NOTFOUND("��¼ʧ�ܣ��û�������"),
    PARAMETER_ERROR("system.validate.error"),
    NOT_FOUND("system.error.not_found"),

    @Deprecated
    PASSWORD_INCORRECT("password incorrect"),
    @Deprecated
    NO_LOGIN("The user is not logged in");

    public String message;

    ResponseCodeEnum(String message) {
        this.message = message;
    }

    public String getCode() {
        return name();
    }
}

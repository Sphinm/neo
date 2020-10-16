package com.example.neo.enums;

public enum ResponseCodeEnum {
    //system
    SUCCESS("SUCCESS"),
    SERVER_ERROR("服务器错误"),
    PASSWORD_ERROR("登录失败，密码错误"),
    INIT_PASSWORD_ERROR("初始密码错误"),
    PASSWORD_EQUALS("密码重复，请确保修改后的密码和之前的不一样"),
    USER_NOTFOUND("登录失败，用户不存在"),
    PARAMETER_ERROR("system.validate.error"),
    NOT_FOUND("system.error.not_found"),
    LOGIN_FAILED("用户名或密码错误"),
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

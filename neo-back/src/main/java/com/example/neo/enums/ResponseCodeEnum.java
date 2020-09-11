package com.example.neo.enums;

public enum ResponseCodeEnum {
    //system
    SUCCESS("SUCCESS"),
    SERVER_ERROR("服务器错误"),
    PASSWORD_ERROR("登录失败，密码错误"),
    USER_NOTFOUND("登录失败，用户不存在"),
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

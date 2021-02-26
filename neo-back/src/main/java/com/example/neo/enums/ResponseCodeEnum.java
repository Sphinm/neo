package com.example.neo.enums;

public enum ResponseCodeEnum {
    //system
    SUCCESS("SUCCESS"),
    SERVER_ERROR("服务器错误"),
    PASSWORD_ERROR("登录失败，密码错误"),
    INIT_PASSWORD_ERROR("初始密码错误"),
    PASSWORD_EQUALS("密码重复，请确保修改后的密码和之前的不一样"),
    USER_NOTFOUND("登录失败，用户不存在"),
    PARAMETER_ERROR("系统参数错误"),
    NOT_FOUND("未找到"),
    LOGIN_FAILED("用户名或密码错误"),
    FILE_NOT_NULL("文件不可为空"),
    FILE_ERROR("文件上传失败"),
    AMOUNT_NOT_VALID("金额不合法"),
    // go on
    CREATE_USER_FAILED("创建用户失败"),
    MOBILE_EQUALS("手机号重复");

    public String message;

    ResponseCodeEnum(String message) {
        this.message = message;
    }

    public String getCode() {
        return name();
    }
}

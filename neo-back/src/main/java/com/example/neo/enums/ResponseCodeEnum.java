package com.example.neo.enums;

public enum ResponseCodeEnum {
    //system
    SUCCESS("SUCCESS"),
    SERVER_ERROR("·şÎñÆ÷´íÎó"),
    PASSWORD_ERROR("µÇÂ¼Ê§°Ü£¬ÃÜÂë´íÎó"),
    USER_NOTFOUND("µÇÂ¼Ê§°Ü£¬ÓÃ»§²»´æÔÚ"),
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

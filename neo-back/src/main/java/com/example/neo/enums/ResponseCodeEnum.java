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
    FILE_ERROR_TYPE("文件格式不正确"),
    SAVE_DATA_ERROR("数据存储异常，请稍后重试！"),
    AMOUNT_NOT_VALID("金额不合法"),
    CREATE_USER_FAILED("创建用户失败"),
    NOT_FOUND_FINANCE("未找到当前公司相关财务信息"),
    WITHDRAW_LESS_AMOUNT("当前提现金额大于账户总金额，属于非法提现"),
    CREATE_WITHDRAW_FAIL("提现失败"),
    WITHDRAW_TODAY("今日已申请提现一次，请等待管理员审核通过"),
    ORDER_NUMBER_MISS("开票信息缺失"),


    MOBILE_EQUALS("手机号重复");
    public String message;

    ResponseCodeEnum(String message) {
        this.message = message;
    }

    public String getCode() {
        return name();
    }
}

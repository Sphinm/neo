package com.example.neo.utils;

import com.example.neo.enums.ResponseCodeEnum;

public class ResponseBean<T> {

    private String code;
    private String message;
    private T data;

    public static <T> ResponseBean success() {
        ResponseBean responseBean = new ResponseBean<T>();
        responseBean.setCode(ResponseCodeEnum.SUCCESS.getCode());
        return responseBean;
    }

    public static <T> ResponseBean success(T data) {
        ResponseBean responseBean = new ResponseBean<T>();
        responseBean.setCode(ResponseCodeEnum.SUCCESS.getCode());
        responseBean.setData(data);
        return responseBean;
    }

    public static <T> ResponseBean<T> fail(ResponseCodeEnum responseCode) {
        ResponseBean<T> responseBean = new ResponseBean<T>();
        responseBean.setCode(responseCode.getCode());
        responseBean.setMessage(responseCode.message);
        return responseBean;
    }

    public static <T> ResponseBean<T> fail(ResponseCodeEnum responseCode, String message) {
        ResponseBean<T> responseBean = new ResponseBean<T>();
        responseBean.setCode(responseCode.getCode());
        responseBean.setMessage(message);
        return responseBean;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

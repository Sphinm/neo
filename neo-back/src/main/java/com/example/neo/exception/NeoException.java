package com.example.neo.exception;

import lombok.Getter;

/**
 * @Author: xuhr
 * @Mail: 969858212@qq.com
 * @Date: 2020/10/16 13:11
 */
@Getter
public class NeoException extends RuntimeException {
    private int code;
    private String message;

    public NeoException(int code,String message){
        this.code=code;
        this.message=message;
    }

    public NeoException(String message){
        this.code=500;
        this.message=message;
    }
}

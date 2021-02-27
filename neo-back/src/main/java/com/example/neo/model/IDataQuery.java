package com.example.neo.model;

import lombok.Data;

import java.util.List;

/**
 * Modal 是给客户端的接口返回数据
 */
@Data
public class IDataQuery {
    Integer id;
    String merchantName;
    Integer totalAmount;
    Double balance;
}

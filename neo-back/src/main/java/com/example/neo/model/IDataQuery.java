package com.example.neo.model;

import lombok.Data;

import java.util.List;

@Data
public class IDataQuery {
    String merchantName;
    String totalAmount;
    String balance;
    List<Object> companyInfo;
}

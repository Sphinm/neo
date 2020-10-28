package com.example.neo.model;

import lombok.Data;

import java.util.List;

@Data
public class IDataQuery {
    Integer id;
    String merchantName;
    Integer totalAmount;
    Double balance;
    List<IDataQueryCompany> companyInfo;
}

package com.example.neo.model;

import com.example.neo.mybatis.model.NeoFinance;
import lombok.Data;

import java.util.List;

@Data
public class IDataQuery {
    Integer id;
    String merchantName;
    Integer totalAmount;
    Double balance;
    List<NeoFinance> companyInfo;
}

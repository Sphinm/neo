package com.example.neo.model;

import com.example.neo.mybatis.model.NeoFinance;
import lombok.Data;

@Data
public class ICompanyList {
    String companyName;
    NeoFinance companyInfo;
}

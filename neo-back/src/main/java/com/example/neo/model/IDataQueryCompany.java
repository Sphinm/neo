package com.example.neo.model;

import lombok.Data;

@Data
public class IDataQueryCompany {
    Integer id;
    String companyName;
    Double totalRecharge;
    Double totalIssued;
    Double balance;
}

package com.example.neo.model;

import com.example.neo.mybatis.model.NeoFinance;
import lombok.Data;

import java.util.List;

@Data
public class IMerchantRebate {
    Double balance;
    List<NeoFinance> rebateList;
}

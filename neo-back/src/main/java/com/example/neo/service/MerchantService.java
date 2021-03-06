package com.example.neo.service;

import com.example.neo.utils.ResponseBean;

public interface MerchantService {
    ResponseBean fetchRebate();

    ResponseBean fetchMerchantBalance();

    ResponseBean fetchMerchantCreateCompany();

    ResponseBean searchByCompanyNameForList(String name);

    ResponseBean searchByCompanyNameForAssign(String id);

    ResponseBean fetchAssignRecords();

    ResponseBean fetchRebateRecords();
}

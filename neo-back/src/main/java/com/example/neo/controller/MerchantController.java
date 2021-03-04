package com.example.neo.controller;

import com.example.neo.service.MerchantService;
import com.example.neo.utils.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MerchantController {
    @Autowired
    MerchantService merchantService;

    @PreAuthorize("hasAnyAuthority('merchant_rebate')")
    @GetMapping("/rebate")
    public ResponseBean fetchRebate() {
        return merchantService.fetchRebate();
    }

    @PreAuthorize("hasAnyAuthority('merchant_rebate')")
    @GetMapping("/merchant/balance")
    public ResponseBean fetchMerchantBalance() {
        return merchantService.fetchMerchantBalance();
    }

    @PreAuthorize("hasAnyAuthority('merchant_rebate')")
    @GetMapping("/create/company-list")
    public ResponseBean fetchMerchantCreateCompany() {
        return merchantService.fetchMerchantCreateCompany();
    }
}

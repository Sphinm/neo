package com.example.neo.controller;

import com.example.neo.model.IWithdraw;
import com.example.neo.service.MerchantService;
import com.example.neo.utils.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("hasAnyAuthority('merchant_rebate')")
    @GetMapping("/search/company")
    public ResponseBean searchByCompanyNameForList(@RequestParam("name") String name) {
        return merchantService.searchByCompanyNameForList(name);
    }

    @PreAuthorize("hasAnyAuthority('merchant_rebate')")
    @GetMapping("/search/company/assign")
    public ResponseBean searchByCompanyNameForAssign(@RequestParam("id") String id) {
        return merchantService.searchByCompanyNameForAssign(id);
    }

    @PreAuthorize("hasAnyAuthority('merchant_rebate')")
    @GetMapping("/fetch/assign/records")
    public ResponseBean fetchAssignRecords() {
        return merchantService.fetchAssignRecords();
    }

    @PreAuthorize("hasAnyAuthority('merchant_rebate')")
    @GetMapping("/fetch/rebate/records")
    public ResponseBean fetchRebateRecords() {
        return merchantService.fetchRebateRecords();
    }

    @PreAuthorize("hasAnyAuthority('merchant_rebate')")
    @PostMapping("/merchant/withdraw")
    public ResponseBean withdrawByMerchant(@RequestBody IWithdraw withdraw) {
        return merchantService.withdrawByMerchant(withdraw);
    }

    @PreAuthorize("hasAnyAuthority('merchant_rebate')")
    @GetMapping("/fetch/withdraw/records")
    public ResponseBean fetchMerchantWithDrawRecords() {
        return merchantService.fetchMerchantWithDrawRecords();
    }
}

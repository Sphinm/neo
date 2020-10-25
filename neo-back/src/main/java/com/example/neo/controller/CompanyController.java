package com.example.neo.controller;

import com.example.neo.model.ICreateUser;
import com.example.neo.service.NeoCompanyService;
import com.example.neo.utils.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 仅客户公司相关的 api
 */

@Slf4j
@RestController
public class CompanyController {
    @Autowired
    NeoCompanyService companyService;

    @PreAuthorize("hasAnyAuthority('user_company')")
    @GetMapping("/fetch/company")
    public ResponseBean fetchCompanyInfo() {
        List<ICreateUser> companyList = companyService.fetchCompanyInfo();
        return ResponseBean.success(companyList);
    }

    @PreAuthorize("hasAnyAuthority('user_company')")
    @PostMapping("/update/company")
    public ResponseBean updateMerchantInfoByType(@RequestBody ICreateUser user) {
        companyService.updateCompanyInfo(user);
        return ResponseBean.success();
    }

    @PreAuthorize("hasAnyAuthority('user_company')")
    @PostMapping("/delete/company")
    public ResponseBean deleteMerchantInfoByType(@RequestParam("merchantId") int merchantId) {
        companyService.deleteCompanyInfo(merchantId);
        return ResponseBean.success();
    }
}

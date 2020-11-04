package com.example.neo.controller;

import com.example.neo.enums.ResponseCodeEnum;
import com.example.neo.model.ICharge;
import com.example.neo.model.ICompanyList;
import com.example.neo.service.NeoCompanyService;
import com.example.neo.utils.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class CompanyController {
    @Autowired
    NeoCompanyService companyService;

    @PreAuthorize("hasAnyAuthority('company_list')")
    @GetMapping("/fetch/company")
    public ResponseBean fetchCompanyList() {
        List<ICompanyList> companyList = companyService.fetchCompanyList();
        return ResponseBean.success(companyList);
    }

    @PostMapping("charge")
    public ResponseBean charge(@RequestParam("file") MultipartFile file, ICharge icharge){
        if (file==null){
            return ResponseBean.fail(ResponseCodeEnum.FILE_NOT_NULL);
        }
        if (icharge==null||icharge.getAmount()<=0){
            return ResponseBean.fail(ResponseCodeEnum.AMOUNT_NOT_VALID);
        }
        return companyService.charge(file,icharge);
    }
}

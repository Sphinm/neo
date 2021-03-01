package com.example.neo.controller;

import com.example.neo.enums.ResponseCodeEnum;
import com.example.neo.model.ICharge;
import com.example.neo.service.NeoCompanyService;
import com.example.neo.utils.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class CompanyController {
    @Autowired
    NeoCompanyService companyService;

    @PreAuthorize("hasAnyAuthority('company_list')")
    @GetMapping("/fetch/company")
    public ResponseBean fetchCompanyList() {
        return companyService.fetchCompanyList();
    }

    @PreAuthorize("hasAnyAuthority('charge_page')")
    @GetMapping("/chargepage")
    public ResponseBean getChargeInfo(){
        return companyService.getChargeInfo();
    }

    @PreAuthorize("hasAnyAuthority('charge_post')")
    @PostMapping("/chargepost")
    public ResponseBean charge(@RequestPart("file") MultipartFile file,@RequestPart("charge")ICharge icharge){
        log.info("{}",icharge.getAmount());
        if (file==null){
            return ResponseBean.fail(ResponseCodeEnum.FILE_NOT_NULL);
        }
        if (icharge == null||icharge.getAmount()<=0){
            return ResponseBean.fail(ResponseCodeEnum.AMOUNT_NOT_VALID);
        }
        return companyService.charge(file,icharge);
    }

    @PreAuthorize("hasAnyAuthority('charge_list')")
    @GetMapping("/chargelist")
    public ResponseBean getChargeList(@RequestParam(value="pageNum" ,required =false, defaultValue = "1") int pageNum,@RequestParam(value="pageSize" ,required =false, defaultValue = "10") int pageSize){
        return companyService.getChargeList(pageNum,pageSize);
    }
}

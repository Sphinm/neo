package com.example.neo.controller;

import com.example.neo.enums.ResponseCodeEnum;
import com.example.neo.model.ICharge;
import com.example.neo.model.IInvoice;
import com.example.neo.service.NeoCompanyService;
import com.example.neo.utils.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("hasAnyAuthority('company_issues')")
    @GetMapping("/company/charge-info")
    public ResponseBean getChargeInfo(){
        return companyService.getChargeInfo();
    }

    @PreAuthorize("hasAnyAuthority('company_issues')")
    @PostMapping("/company/charge/post")
    public ResponseBean charge(@RequestBody() ICharge icharge){
        if (icharge.getVirtualPath()==null){
            return ResponseBean.fail(ResponseCodeEnum.FILE_NOT_NULL);
        }
        if (icharge.getAmount()<=0){
            return ResponseBean.fail(ResponseCodeEnum.AMOUNT_NOT_VALID);
        }
        return companyService.charge(icharge);
    }

    @PreAuthorize("hasAnyAuthority('company_issues')")
    @GetMapping("/company/charge-list")
    public ResponseBean getChargeList(@RequestParam(value="pageNum" ,required =false, defaultValue = "1") int pageNum,@RequestParam(value="pageSize" ,required =false, defaultValue = "10") int pageSize){
        return companyService.getChargeList(pageNum,pageSize);
    }

    // 财务中心
    @PreAuthorize("hasAnyAuthority('company_issues')")
    @PostMapping("/company/create-invoice")
    public ResponseBean companyInvoice(@RequestBody() IInvoice invoice){
        if (invoice.getOrderNumberList().size() == 0 || invoice.getTotalMoney() <= 0
            || invoice.getInvoiceContent().equals("")
            || invoice.getRecipientName().equals("")
            || invoice.getRecipientTel().equals("")
            || invoice.getRecipientAddress().equals("")) {
            return ResponseBean.fail(ResponseCodeEnum.ORDER_NUMBER_MISS);
        }
        return companyService.companyInvoice(invoice);
    }

    @PreAuthorize("hasAnyAuthority('company_issues')")
    @GetMapping("/company/invoice-list")
    public ResponseBean getInvoiceList(){
        return companyService.getInvoiceList();
    }

    @PreAuthorize("hasAnyAuthority('company_issues')")
    @GetMapping("/company/receipts")
    public ResponseBean getReceipts(){
        return companyService.getReceipts();
    }

}

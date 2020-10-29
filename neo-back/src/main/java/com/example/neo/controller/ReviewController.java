package com.example.neo.controller;

import com.example.neo.model.IReviewCompany;
import com.example.neo.mybatis.model.*;
import com.example.neo.service.ReviewService;
import com.example.neo.utils.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @PreAuthorize("hasAnyAuthority('review_company')")
    @GetMapping("/review/companyList")
    public ResponseBean fetchReviewCompanyList() {
        List<IReviewCompany> reviewCompanyList= reviewService.fetchReviewCompanyList();
        return ResponseBean.success(reviewCompanyList);
    }

    @PreAuthorize("hasAnyAuthority('review_company')")
    @PostMapping("/review/company")
    public ResponseBean reviewCompany(@RequestParam("id") int id) {
        reviewService.reviewCompany(id);
        return ResponseBean.success();
    }

    @PreAuthorize("hasAnyAuthority('review_recharge')")
    @GetMapping("/review/rechargeList")
    public ResponseBean fetchReviewRechargeList() {
        List<NeoRechargeRecord> reviewRechargeList= reviewService.fetchReviewRechargeList();
        return ResponseBean.success(reviewRechargeList);
    }

    @PreAuthorize("hasAnyAuthority('review_recharge')")
    @PostMapping("/review/recharge")
    public ResponseBean reviewRecharge(@RequestParam("id") int id) {
        reviewService.reviewRecharge(id);
        return ResponseBean.success();
    }

    @PreAuthorize("hasAnyAuthority('review_invoice')")
    @GetMapping("/review/invoiceList")
    public ResponseBean fetchReviewInvoiceList() {
        List<NeoInvoice> reviewInvoiceList= reviewService.fetchReviewInvoiceList();
        return ResponseBean.success(reviewInvoiceList);
    }

    @PreAuthorize("hasAnyAuthority('review_invoice')")
    @PostMapping("/review/invoice")
    public ResponseBean reviewInvoice(@RequestParam("id") int id) {
        reviewService.reviewInvoice(id);
        return ResponseBean.success();
    }

    @PreAuthorize("hasAnyAuthority('review_provide')")
    @GetMapping("/review/provideList")
    public ResponseBean fetchReviewProvideList() {
        List<NeoIssue> reviewProvideList= reviewService.fetchReviewProvideList();
        return ResponseBean.success(reviewProvideList);
    }

    @PreAuthorize("hasAnyAuthority('review_provide')")
    @PostMapping("/review/provide")
    public ResponseBean reviewProvide(@RequestParam("id") int id) {
        reviewService.reviewProvide(id);
        return ResponseBean.success();
    }

    @PreAuthorize("hasAnyAuthority('review_withdraw')")
    @GetMapping("/review/withdrawList")
    public ResponseBean fetchReviewWithdrawList() {
        List<NeoWithdraw> reviewWithdrawList= reviewService.fetchReviewWithdrawList();
        return ResponseBean.success(reviewWithdrawList);
    }

    @PreAuthorize("hasAnyAuthority('review_withdraw')")
    @PostMapping("/review/withdraw")
    public ResponseBean reviewWithdraw(@RequestParam("id") int id) {
        reviewService.reviewWithdraw(id);
        return ResponseBean.success();
    }

    @PreAuthorize("hasAnyAuthority('review_tax')")
    @GetMapping("/review/taxList")
    public ResponseBean fetchReviewTaxList() {
        List<NeoCompanyTax> reviewTaxList= reviewService.fetchReviewTaxList();
        return ResponseBean.success(reviewTaxList);
    }

    @PreAuthorize("hasAnyAuthority('review_tax')")
    @PostMapping("/review/tax")
    public ResponseBean reviewTax(@RequestParam("id") int id) {
        reviewService.reviewTax(id);
        return ResponseBean.success();
    }

    @PreAuthorize("hasAnyAuthority('review_tax')")
    @PostMapping("/upload/tax")
    public ResponseBean uploadTax() {
        reviewService.uploadTax();
        return ResponseBean.success();
    }
}

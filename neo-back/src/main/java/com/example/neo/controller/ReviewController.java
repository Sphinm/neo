package com.example.neo.controller;

import com.example.neo.service.ReviewService;
import com.example.neo.utils.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @PreAuthorize("hasAnyAuthority('review_company')")
    @GetMapping("/review/companyList")
    public ResponseBean fetchReviewCompanyList() {
        return reviewService.fetchReviewCompanyList();
    }

    @PreAuthorize("hasAnyAuthority('review_company')")
    @PostMapping("/review/company")
    public ResponseBean reviewCompany(@RequestParam("id") int id) {
        return reviewService.reviewCompany(id);
    }

    @PreAuthorize("hasAnyAuthority('review_recharge')")
    @GetMapping("/review/rechargeList")
    public ResponseBean fetchReviewRechargeList() {
        return reviewService.fetchReviewRechargeList();
    }

    @PreAuthorize("hasAnyAuthority('review_recharge')")
    @PostMapping("/review/recharge")
    public ResponseBean reviewRecharge(@RequestParam("id") int id) {
        return reviewService.reviewRecharge(id);
    }

    @PreAuthorize("hasAnyAuthority('review_invoice')")
    @GetMapping("/review/invoiceList")
    public ResponseBean fetchReviewInvoiceList() {
        return reviewService.fetchReviewInvoiceList();
    }

    @PreAuthorize("hasAnyAuthority('review_invoice')")
    @PostMapping("/review/invoice")
    public ResponseBean reviewInvoice(@RequestParam("id") int id) {
        return reviewService.reviewInvoice(id);
    }

    @PreAuthorize("hasAnyAuthority('review_provide')")
    @GetMapping("/review/provideList")
    public ResponseBean fetchReviewProvideList() {
        return reviewService.fetchReviewProvideList();
    }

    @PreAuthorize("hasAnyAuthority('review_provide')")
    @PostMapping("/review/provide")
    public ResponseBean reviewProvide(@RequestParam("id") int id) {
        return reviewService.reviewProvide(id);
    }

    @PreAuthorize("hasAnyAuthority('review_withdraw')")
    @GetMapping("/review/withdrawList")
    public ResponseBean fetchReviewWithdrawList() {
        return reviewService.fetchReviewWithdrawList();
    }

    @PreAuthorize("hasAnyAuthority('review_withdraw')")
    @PostMapping("/review/withdraw")
    public ResponseBean reviewWithdraw(@RequestParam("id") int id) {
        return reviewService.reviewWithdraw(id);
    }

    @PreAuthorize("hasAnyAuthority('review_tax')")
    @GetMapping("/review/taxList")
    public ResponseBean fetchReviewTaxList() {
        return reviewService.fetchReviewTaxList();
    }

    @PreAuthorize("hasAnyAuthority('review_tax')")
    @PostMapping("/review/tax")
    public ResponseBean reviewTax(@RequestParam("id") int id) {
        return reviewService.reviewTax(id);
    }

    @PreAuthorize("hasAnyAuthority('review_tax')")
    @PostMapping("/upload/tax")
    public ResponseBean uploadTax() {
        return reviewService.uploadTax();
    }
}

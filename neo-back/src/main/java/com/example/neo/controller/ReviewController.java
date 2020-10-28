package com.example.neo.controller;

import com.example.neo.model.IReviewCompany;
import com.example.neo.service.ReviewService;
import com.example.neo.utils.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseBean reviewCompany() {
        reviewService.reviewCompany();
        return ResponseBean.success();
    }
}

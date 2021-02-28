package com.example.neo.service;

import com.example.neo.utils.ResponseBean;
import org.springframework.web.multipart.MultipartFile;

public interface ReviewService {
    ResponseBean fetchReviewCompanyList();

    ResponseBean reviewCompany(int id);

    ResponseBean fetchReviewRechargeList();

    ResponseBean reviewRecharge(int id);

    ResponseBean fetchReviewInvoiceList();

    ResponseBean reviewInvoice(int id);

    ResponseBean fetchReviewProvideList();

    ResponseBean reviewProvide(int id);

    ResponseBean fetchReviewWithdrawList();

    ResponseBean reviewWithdraw(int id);

    ResponseBean fetchReviewTaxList();

    ResponseBean reviewTax(int id);

    ResponseBean uploadTax(MultipartFile file);
}

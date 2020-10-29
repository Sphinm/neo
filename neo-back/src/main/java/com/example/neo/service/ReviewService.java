package com.example.neo.service;

import com.example.neo.model.IReviewCompany;
import com.example.neo.mybatis.model.*;

import java.util.List;

public interface ReviewService {
    List<IReviewCompany> fetchReviewCompanyList();

    void reviewCompany(int id);

    List<NeoRechargeRecord> fetchReviewRechargeList();

    void reviewRecharge(int id);

    List<NeoInvoice> fetchReviewInvoiceList();

    void reviewInvoice(int id);

    List<NeoIssue> fetchReviewProvideList();

    void reviewProvide(int id);

    List<NeoWithdraw> fetchReviewWithdrawList();

    void reviewWithdraw(int id);

    List<NeoCompanyTax> fetchReviewTaxList();

    void reviewTax(int id);

    void uploadTax();
}

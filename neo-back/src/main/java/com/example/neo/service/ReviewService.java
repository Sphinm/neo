package com.example.neo.service;

import com.example.neo.model.IReviewCompany;

import java.util.List;

public interface ReviewService {
    List<IReviewCompany> fetchReviewCompanyList();

    void reviewCompany();
}

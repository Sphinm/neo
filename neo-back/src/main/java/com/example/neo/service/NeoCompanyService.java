package com.example.neo.service;

import com.example.neo.model.ICreateUser;

import java.util.List;

public interface NeoCompanyService {
    List<ICreateUser> fetchCompanyInfo();

    void updateCompanyInfo(ICreateUser user);

    void deleteCompanyInfo(int id);
}

package com.example.neo.service;

import com.example.neo.model.ICharge;
import com.example.neo.model.ICompanyList;
import com.example.neo.mybatis.model.NeoCompany;
import com.example.neo.utils.ResponseBean;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NeoCompanyService {
    List<ICompanyList> fetchCompanyList();
    ResponseBean charge(MultipartFile file, ICharge icharge);
}

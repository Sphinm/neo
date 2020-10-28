package com.example.neo.service.Impl;

import com.example.neo.model.ICompanyList;
import com.example.neo.mybatis.model.NeoFinanceExample;
import com.example.neo.service.NeoCompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NeoCompanyServiceImpl implements NeoCompanyService {
    @Override
    public List<ICompanyList> fetchCompanyList() {
        ICompanyList company = new ICompanyList();
        NeoFinanceExample example = new NeoFinanceExample();
//        example.
        return null;
    }
}

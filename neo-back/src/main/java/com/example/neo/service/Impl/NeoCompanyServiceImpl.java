package com.example.neo.service.Impl;

import com.example.neo.model.ICompanyList;
import com.example.neo.mybatis.mapper.NeoCompanyMapper;
import com.example.neo.mybatis.model.NeoCompanyExample;
import com.example.neo.service.NeoCompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NeoCompanyServiceImpl implements NeoCompanyService {
    @Autowired
    NeoCompanyMapper companyMapper;

    @Override
    public List<ICompanyList> fetchCompanyList() {
        ICompanyList company = new ICompanyList();
        NeoCompanyExample companyExample = new NeoCompanyExample();
//        NeoFinanceExample financeExample = new NeoFinanceExample();
//        companyExample.createCriteria().andCompanyTypeEqualTo(false);
//        List<NeoCompany> companyList = companyMapper.selectByExample(companyExample);
//        for (NeoCompany item : companyList) {
//            log.info("{}", item);
//        }
        return null;
    }
}

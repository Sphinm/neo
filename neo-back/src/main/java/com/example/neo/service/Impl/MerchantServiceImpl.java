package com.example.neo.service.Impl;

import com.example.neo.mybatis.mapper.NeoCompanyMapper;
import com.example.neo.mybatis.mapper.NeoFinanceMapper;
import com.example.neo.mybatis.mapper.NeoIssueMapper;
import com.example.neo.mybatis.model.*;
import com.example.neo.service.MerchantService;
import com.example.neo.utils.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    CommonService commonService;
    @Autowired
    MerchantService merchantService;
    @Autowired
    NeoCompanyMapper companyMapper;
    @Autowired
    NeoIssueMapper issueMapper;
    @Autowired
    NeoFinanceMapper financeMapper;

    @Override
    public ResponseBean fetchRebate() {
        NeoCompany company = commonService.fetchCurrentCompany();
        NeoIssueExample example = new NeoIssueExample();
        example.createCriteria().andCompanyIdEqualTo(company.getId());
        return ResponseBean.success(issueMapper.selectByExample(example));
    }

    @Override
    public ResponseBean fetchMerchantBalance() {
        NeoCompany company = commonService.fetchCurrentCompany();
        NeoFinanceExample example = new NeoFinanceExample();
        example.createCriteria().andCompanyIdEqualTo(company.getId()).andStatusEqualTo(true);
        List<NeoFinance> financeList = financeMapper.selectByExample(example);
        if (financeList == null || financeList.size() == 0) {
            return ResponseBean.success(0);
        }
        return ResponseBean.success(financeList.get(0).getBalance());
    }

    @Override
    public ResponseBean fetchMerchantCreateCompany() {
        NeoUser user = commonService.fetchUserByMobile();
        NeoCompanyExample companyExample = new NeoCompanyExample();
        companyExample.setOrderByClause("create_date desc");
        companyExample.createCriteria().andCreatorIdEqualTo(user.getId());
        List<NeoCompany> companyList = companyMapper.selectByExample(companyExample);
        return ResponseBean.success(companyList);
    }
}

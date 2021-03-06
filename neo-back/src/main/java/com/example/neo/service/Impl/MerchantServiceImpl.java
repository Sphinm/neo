package com.example.neo.service.Impl;

import com.example.neo.model.IIssue;
import com.example.neo.mybatis.mapper.NeoCompanyMapper;
import com.example.neo.mybatis.mapper.NeoFinanceMapper;
import com.example.neo.mybatis.mapper.NeoIssueMapper;
import com.example.neo.mybatis.model.*;
import com.example.neo.service.MerchantService;
import com.example.neo.utils.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public ResponseBean searchByCompanyNameForList(String name) {
        NeoUser user = commonService.fetchUserByMobile();
        NeoCompanyExample example = new NeoCompanyExample();
        // 查找是该代理商名下用户的公司
        if (name.equals("")) {
            example.setOrderByClause("create_date desc");
            example.createCriteria().andCreatorIdEqualTo(user.getId());
        } else {
            example.createCriteria().andCreatorIdEqualTo(user.getId()).andCompanyNameLike("%" + name + "%");
        }
        List<NeoCompany> companyList = companyMapper.selectByExample(example);
        return ResponseBean.success(companyList);
    }

    @Override
    public ResponseBean searchByCompanyNameForAssign(String id) {
        if (id.equals("")) {
            return ResponseBean.success(fetchMerchantAssignCommon());
        }
        List<IIssue> data = fetchMerchantAssignCommon();
        List<IIssue> issues = new ArrayList<>();
        for (IIssue issue: data) {
            if (issue.getOrderNumber().equals(id)) {
                issues.add(issue);
            }
        }
        return ResponseBean.success(issues);
    }

    @Override
    public ResponseBean fetchAssignRecords() {
        return ResponseBean.success(fetchMerchantAssignCommon());
    }

    private List<IIssue> fetchMerchantAssignCommon() {
        NeoUser user = commonService.fetchUserByMobile();
        NeoCompanyExample companyExample = new NeoCompanyExample();
        companyExample.createCriteria().andCreatorIdEqualTo(user.getId());
        List<NeoCompany> companyList = companyMapper.selectByExample(companyExample);

        List<IIssue> issues = new ArrayList<>();

        for (NeoCompany company: companyList) {

            String companyName = commonService.fetchCompanyNameById(company.getId());
            NeoIssueExample example = new NeoIssueExample();
            // 首先判断是该代理商旗下的公司，其次判断发放状态是成功的，最后判断审核状态为已通过的
            example.createCriteria().andCompanyIdEqualTo(company.getId()).andStatusEqualTo(true).andProvideStatusEqualTo(true);
            List<NeoIssue> issueList = issueMapper.selectByExample(example);
            for (NeoIssue item: issueList) {
                IIssue issue = new IIssue();
                issue.setCompanyName(companyName);
                issue.setCreateDate(item.getCreateDate());
                issue.setRebate(item.getRebate());
                issue.setAmount(item.getAmount());
                issue.setTaskName(item.getTaskName());
                issue.setOrderNumber(item.getOrderNumber());
                issues.add(issue);
            }
        }
        return issues;
    }

    @Override
    public ResponseBean fetchRebateRecords() {
        return ResponseBean.success();
    }
}

package com.example.neo.service.Impl;

import com.example.neo.model.IReviewCompany;
import com.example.neo.mybatis.mapper.NeoCompanyMapper;
import com.example.neo.mybatis.mapper.NeoCompanyRelationMapper;
import com.example.neo.mybatis.model.NeoCompany;
import com.example.neo.mybatis.model.NeoCompanyRelation;
import com.example.neo.mybatis.model.NeoCompanyRelationExample;
import com.example.neo.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    NeoCompanyRelationMapper relationMapper;
    @Autowired
    NeoCompanyMapper companyMapper;

    @Override
    public List<IReviewCompany> fetchReviewCompanyList() {
        NeoCompanyRelationExample example = new NeoCompanyRelationExample();
        example.createCriteria().andIsCheckedEqualTo(false);
        List<NeoCompanyRelation> relationList = relationMapper.selectByExample(example);
        List<IReviewCompany> list = new ArrayList<>();
        for (NeoCompanyRelation item : relationList) {
            IReviewCompany dto = new IReviewCompany();
            dto.setId(item.getId());
            NeoCompany company = companyMapper.selectByPrimaryKey(item.getCompanyId());
            dto.setCompanyName(company.getCompanyName());
            dto.setBankName(company.getCompanyBankName());
            dto.setBankCode(company.getCompanyBankNumber());
            dto.setCompanyAddress(company.getCompanyLocation());
            dto.setCompanyTax(company.getCompanyTax());
            dto.setContactName(company.getContactName());
            dto.setContactTel(company.getContactTel());
            dto.setRate(company.getCompanyRate());
            dto.setIsChecked(item.getIsChecked());
            dto.setCheckTime(item.getUpdateDate());
            list.add(dto);
        }
        return list;
    }

    /**
     * 审核客户公司需要将 neo_user 表中的 lock 和 neo_company_relation 表中的 lock 打开
     */
    @Override
    public void reviewCompany() {

    }
}

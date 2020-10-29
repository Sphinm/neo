package com.example.neo.service.Impl;

import com.example.neo.model.IReviewCompany;
import com.example.neo.mybatis.mapper.*;
import com.example.neo.mybatis.model.*;
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
    @Autowired
    NeoUserMapper userMapper;
    @Autowired
    NeoRechargeRecordMapper rechargeRecordMapper;
    @Autowired
    NeoInvoiceMapper invoiceMapper;
    @Autowired
    NeoIssueMapper issueMapper;
    @Autowired
    NeoWithdrawMapper withdrawMapper;
    @Autowired
    NeoCompanyTaxMapper taxMapper;

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
    public void reviewCompany(int id) {
        NeoCompanyRelation relation = relationMapper.selectByPrimaryKey(id);
        relation.setIsChecked(true);
        relationMapper.updateByPrimaryKeySelective(relation);
        NeoUserExample example = new NeoUserExample();
        example.createCriteria().andRelatedIdEqualTo(relation.getCompanyId());
        List<NeoUser> userList = userMapper.selectByExample(example);
        NeoUser user = userList.get(0);
        user.setIsLocked(true);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public List<NeoRechargeRecord> fetchReviewRechargeList() {
        NeoRechargeRecordExample example = new NeoRechargeRecordExample();
        example.createCriteria().andApprovalStatusEqualTo(false);
        return rechargeRecordMapper.selectByExample(example);
    }

    @Override
    public void reviewRecharge(int id) {
        NeoRechargeRecord record = rechargeRecordMapper.selectByPrimaryKey(id);
        record.setApprovalStatus(true);
        rechargeRecordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<NeoInvoice> fetchReviewInvoiceList() {
        NeoInvoiceExample example = new NeoInvoiceExample();
        example.createCriteria().andStatusEqualTo(false);
        return invoiceMapper.selectByExample(example);
    }

    @Override
    public void reviewInvoice(int id) {
        NeoInvoice invoice = invoiceMapper.selectByPrimaryKey(id);
        invoice.setStatus(true);
        invoiceMapper.updateByPrimaryKeySelective(invoice);
    }

    @Override
    public List<NeoIssue> fetchReviewProvideList() {
        NeoIssueExample example = new NeoIssueExample();
        example.createCriteria().andProvideStatusEqualTo(false);
        return issueMapper.selectByExample(example);
    }

    @Override
    public void reviewProvide(int id) {
        NeoIssue record = issueMapper.selectByPrimaryKey(id);
        record.setProvideStatus(true);
        issueMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<NeoWithdraw> fetchReviewWithdrawList() {
        NeoWithdrawExample example = new NeoWithdrawExample();
        example.createCriteria().andStatusEqualTo(false);
        return withdrawMapper.selectByExample(example);
    }

    @Override
    public void reviewWithdraw(int id) {
        NeoWithdraw record = withdrawMapper.selectByPrimaryKey(id);
        record.setStatus(true);
        withdrawMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<NeoCompanyTax> fetchReviewTaxList() {
        NeoCompanyTaxExample example = new NeoCompanyTaxExample();
        example.createCriteria().andIsDeleteEqualTo(false);
        return taxMapper.selectByExample(example);
    }

    @Override
    public void reviewTax(int id) {
        NeoCompanyTax record = taxMapper.selectByPrimaryKey(id);
        record.setIsDelete(true);
        taxMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void uploadTax() {

    }
}

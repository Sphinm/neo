package com.example.neo.service.Impl;

import com.example.neo.enums.ResponseCodeEnum;
import com.example.neo.model.IReviewCompany;
import com.example.neo.mybatis.mapper.*;
import com.example.neo.mybatis.model.*;
import com.example.neo.service.ReviewService;
import com.example.neo.utils.ResponseBean;
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
    public ResponseBean fetchReviewCompanyList() {

        try {
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
            return ResponseBean.success(list);
        } catch (Exception e) {
            return ResponseBean.fail(ResponseCodeEnum.SERVER_ERROR);
        }
    }

    /**
     * 审核客户公司需要将 neo_user 表中的 lock 和 neo_company_relation 表中的 lock 打开
     */
    @Override
    public ResponseBean reviewCompany(int id) {
        try {
            NeoCompanyRelation relation = relationMapper.selectByPrimaryKey(id);
            relation.setIsChecked(true);
            relationMapper.updateByPrimaryKeySelective(relation);
            NeoUserExample example = new NeoUserExample();
            example.createCriteria().andRelatedIdEqualTo(relation.getCompanyId());
            List<NeoUser> userList = userMapper.selectByExample(example);
            NeoUser user = userList.get(0);
            user.setIsLocked(true);
            userMapper.updateByPrimaryKeySelective(user);
            return ResponseBean.success();
        } catch (Exception e) {
            return ResponseBean.fail(ResponseCodeEnum.SERVER_ERROR);
        }
    }

    @Override
    public ResponseBean fetchReviewRechargeList() {
        try {
            NeoRechargeRecordExample example = new NeoRechargeRecordExample();
            example.createCriteria().andApprovalStatusEqualTo(false);
            return ResponseBean.success(rechargeRecordMapper.selectByExample(example));
        } catch (Exception e) {
            return ResponseBean.fail(ResponseCodeEnum.SERVER_ERROR);
        }
    }

    @Override
    public ResponseBean reviewRecharge(int id) {
        try {
            NeoRechargeRecord record = rechargeRecordMapper.selectByPrimaryKey(id);
            record.setApprovalStatus(true);
            rechargeRecordMapper.updateByPrimaryKeySelective(record);
            return ResponseBean.success();
        } catch (Exception e) {
            return ResponseBean.fail(ResponseCodeEnum.SERVER_ERROR);
        }
    }

    @Override
    public ResponseBean fetchReviewInvoiceList() {
        try {
            NeoInvoiceExample example = new NeoInvoiceExample();
            example.createCriteria().andStatusEqualTo(false);
            return ResponseBean.success(invoiceMapper.selectByExample(example));
        } catch (Exception e) {
            return ResponseBean.fail(ResponseCodeEnum.SERVER_ERROR);
        }
    }

    @Override
    public ResponseBean reviewInvoice(int id) {
        try {
            NeoInvoice invoice = invoiceMapper.selectByPrimaryKey(id);
            invoice.setStatus(true);
            invoiceMapper.updateByPrimaryKeySelective(invoice);
            return ResponseBean.success();
        } catch (Exception e) {
            return ResponseBean.fail(ResponseCodeEnum.SERVER_ERROR);
        }
    }

    @Override
    public ResponseBean fetchReviewProvideList() {
        try {
            NeoIssueExample example = new NeoIssueExample();
            example.createCriteria().andProvideStatusEqualTo(false);
            return ResponseBean.success(issueMapper.selectByExample(example));
        } catch (Exception e) {
            return ResponseBean.fail(ResponseCodeEnum.SERVER_ERROR);
        }
    }

    @Override
    public ResponseBean reviewProvide(int id) {
        try {
            NeoIssue record = issueMapper.selectByPrimaryKey(id);
            record.setProvideStatus(true);
            issueMapper.updateByPrimaryKeySelective(record);
            return ResponseBean.success();
        } catch (Exception e) {
            return ResponseBean.fail(ResponseCodeEnum.SERVER_ERROR);
        }
    }

    @Override
    public ResponseBean fetchReviewWithdrawList() {
        NeoWithdrawExample example = new NeoWithdrawExample();
        example.createCriteria().andStatusEqualTo(false);
        try {
            return ResponseBean.success(withdrawMapper.selectByExample(example));
        } catch (Exception e) {
            return ResponseBean.fail(ResponseCodeEnum.SERVER_ERROR);
        }
    }

    @Override
    public ResponseBean reviewWithdraw(int id) {
        NeoWithdraw record = withdrawMapper.selectByPrimaryKey(id);
        record.setStatus(true);
        withdrawMapper.updateByPrimaryKeySelective(record);
        try {
            return ResponseBean.success();
        } catch (Exception e) {
            return ResponseBean.fail(ResponseCodeEnum.SERVER_ERROR);
        }
    }

    @Override
    public ResponseBean fetchReviewTaxList() {
        NeoCompanyTaxExample example = new NeoCompanyTaxExample();
        example.createCriteria().andIsDeleteEqualTo(false);
        try {
            return ResponseBean.success(taxMapper.selectByExample(example));
        } catch (Exception e) {
            return ResponseBean.fail(ResponseCodeEnum.SERVER_ERROR);
        }
    }

    @Override
    public ResponseBean reviewTax(int id) {
        NeoCompanyTax record = taxMapper.selectByPrimaryKey(id);
        record.setIsDelete(true);
        taxMapper.updateByPrimaryKeySelective(record);
        try {
            return ResponseBean.success();
        } catch (Exception e) {
            return ResponseBean.fail(ResponseCodeEnum.SERVER_ERROR);
        }
    }

    @Override
    public ResponseBean uploadTax() {
        try {
            return ResponseBean.success();
        } catch (Exception e) {
            return ResponseBean.fail(ResponseCodeEnum.SERVER_ERROR);
        }
    }
}

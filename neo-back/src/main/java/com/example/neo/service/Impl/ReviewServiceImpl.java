package com.example.neo.service.Impl;

import com.example.neo.enums.ResponseCodeEnum;
import com.example.neo.model.IReviewCompany;
import com.example.neo.model.IUploadTaxInfo;
import com.example.neo.mybatis.mapper.*;
import com.example.neo.mybatis.model.*;
import com.example.neo.service.ReviewService;
import com.example.neo.utils.ResponseBean;
import com.example.neo.utils.Snowflake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ReviewServiceImpl implements ReviewService {
    private Snowflake snowflake = new Snowflake(2,3);
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

    @Autowired
    private CommonService commonService;

    @Value("${neo.upload.tax}")
    private String filePath;

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
        try {
            withdrawMapper.updateByPrimaryKeySelective(record);
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

    /**
     * 删除完税凭证
     * @param id
     * @return
     */
    @Override
    public ResponseBean reviewTax(int id) {
        NeoCompanyTax record = taxMapper.selectByPrimaryKey(id);
        record.setIsDelete(true);
        try {
            taxMapper.updateByPrimaryKeySelective(record);
            return ResponseBean.success();
        } catch (Exception e) {
            return ResponseBean.fail(ResponseCodeEnum.SERVER_ERROR);
        }
    }

    @Override
    public ResponseBean uploadTax(MultipartFile file) {
        if (file.isEmpty() || file.getSize() <= 0){
            return ResponseBean.fail(ResponseCodeEnum.FILE_NOT_NULL);
        }
        String fileName = file.getOriginalFilename();
        String dest = this.filePath + fileName;
        String virtualPath = "http://www.axinlinggong.com/images/tax/" + fileName;
        File newFile = new File(dest);
        try {
            file.transferTo(newFile);
            return ResponseBean.success(virtualPath);
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseBean.fail(ResponseCodeEnum.FILE_ERROR);
        }
    }

    @Override
    public ResponseBean uploadTaxInfo(IUploadTaxInfo info) {
        NeoUser user = commonService.fetchUserByMobile();
        Date date = new Date();
        NeoCompanyTax tax = new NeoCompanyTax();
        tax.setCompanyId(info.getCompanyId());
        tax.setIsDelete(false);
        tax.setMonth(info.getMonth());
        tax.setNumber(String.valueOf(snowflake.nextId()));
        tax.setTaxReceive(info.getReceipts());
        tax.setRemark(info.getRemarks());
        tax.setCreatorId(user.getId());
        tax.setCreateDate(date);
        tax.setUpdateId(user.getId());
        tax.setUpdateDate(date);
        try {
            taxMapper.insert(tax);
            return ResponseBean.success();
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseBean.fail(ResponseCodeEnum.FILE_ERROR);
        }
    }
}

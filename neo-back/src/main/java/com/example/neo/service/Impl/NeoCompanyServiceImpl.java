package com.example.neo.service.Impl;

import com.example.neo.exception.NeoException;
import com.example.neo.model.ICharge;
import com.example.neo.model.IInvoice;
import com.example.neo.model.ITaxReceipt;
import com.example.neo.mybatis.mapper.*;
import com.example.neo.mybatis.model.*;
import com.example.neo.service.NeoCompanyService;
import com.example.neo.utils.DoubleUtil;
import com.example.neo.utils.ResponseBean;
import com.example.neo.utils.Snowflake;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@PropertySource({"classpath:application.yml"})
public class NeoCompanyServiceImpl implements NeoCompanyService {
    @Autowired
    private NeoRechargeRecordMapper rechargeRecordMapper;
    @Autowired
    private NeoFinanceMapper financeMapper;
    @Autowired
    private NeoCompanyMapper companyMapper;
    @Autowired
    private NeoInvoiceMapper invoiceMapper;
    @Autowired
    private NeoCompanyTaxMapper taxMapper;
    @Autowired
    private CommonService commonService;

    @Value("${neo.charge.filepath}")
    private String filePath;

    @Override
    /**
     * 仅管理可查看所有公司
     */
    public ResponseBean fetchCompanyList() {
        NeoCompanyExample companyExample = new NeoCompanyExample();
        companyExample.createCriteria().andCompanyTypeEqualTo(false);
        List<NeoCompany> companyList = companyMapper.selectByExample(companyExample);
        return ResponseBean.success(companyList);
    }

    @Override
    public ResponseBean charge(ICharge icharge) {
        //获取当前关联公司信息
        NeoCompany company = commonService.fetchCurrentCompany();
        double amount = icharge.getAmount();
        float rate = company.getCompanyRate() / 100;
        if (!company.getCompanyType()){
            throw new NeoException("代理商不可进行充值操作");
        }
        NeoRechargeRecord rechargeRecord = new NeoRechargeRecord();
        rechargeRecord.setCompanyId(company.getId());
        //订单号使用雪花id
        rechargeRecord.setOrderNumber(String.valueOf(Snowflake.INSTANCE.nextId()));
        rechargeRecord.setCompanyId(company.getId());
        rechargeRecord.setRate(rate*100);
        rechargeRecord.setPaymentAmount(DoubleUtil.formatDouble(amount));
        rechargeRecord.setAccountAmount(DoubleUtil.formatDouble((1-rate)*amount));
        rechargeRecord.setPaymentVoucher(icharge.getVirtualPath());
        rechargeRecord.setInvoicingStatus(false);
        rechargeRecord.setApprovalStatus(false);
        rechargeRecord.setCreatorId(commonService.fetchUserByMobile().getId());
        rechargeRecord.setCreateDate(new Date());
        rechargeRecord.setUpdateId(commonService.fetchUserByMobile().getId());
        rechargeRecord.setUpdateDate(new Date());
        //添加充值记录
        rechargeRecordMapper.insert(rechargeRecord);
        //充值，更改财务表，余额和充值总额
        refreshFinance(company, amount,true);
        return ResponseBean.success("充值成功");
    }

    @Override
    public ResponseBean getChargeInfo() {
        return ResponseBean.success(commonService.fetchCurrentCompany());
    }

    @Override
    public ResponseBean getChargeList(int pageNum,int pageSize) {
        NeoCompany company = commonService.fetchCurrentCompany();
        NeoRechargeRecordExample rechargeRecordExample = new NeoRechargeRecordExample();
        rechargeRecordExample.createCriteria().andCompanyIdEqualTo(company.getId()).andInvoicingStatusEqualTo(false);
        PageHelper.startPage(pageNum,pageSize);
        List<NeoRechargeRecord> records = rechargeRecordMapper.selectByExample(rechargeRecordExample);
        PageInfo<NeoRechargeRecord> pageInfo = new PageInfo<>(records);
        PageInfo<NeoRechargeRecord> newPageInfo = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo,newPageInfo);
        List<NeoRechargeRecord> mid = pageInfo.getList();
        List<NeoRechargeRecord> results = new ArrayList<>();
        if (mid==null||mid.size()==0) {
            return ResponseBean.success(newPageInfo);
        }
        for (NeoRechargeRecord record : mid){
            NeoRechargeRecord record1 = new NeoRechargeRecord();
            BeanUtils.copyProperties(record, record1);
            results.add(record1);
        }
        newPageInfo.setList(results);
        return ResponseBean.success(newPageInfo);
    }

    /**
     * 申请发票
     *
     * @param invoice
     */
    @Override
    public ResponseBean companyInvoice(IInvoice invoice) {
        NeoUser user = commonService.fetchUserByMobile();
        NeoCompany company = commonService.fetchCurrentCompany();
        NeoInvoice dto = new NeoInvoice();
        dto.setOrderNumber(invoice.getOrderNumberList().get(0));
        dto.setInvoiceType(invoice.getInvoiceType());
        dto.setInvoiceAmount(invoice.getTotalMoney());
        dto.setInvoiceContent(invoice.getInvoiceContent());
        dto.setInvoiceCompany(company.getCompanyName());
        dto.setReceiveName(invoice.getRecipientName());
        dto.setReceiveTel(invoice.getRecipientTel());
        dto.setReceiveAddress(invoice.getRecipientAddress());
        dto.setStatus(false);
        dto.setCreatorId(user.getId());
        dto.setCreateDate(new Date());
        updateRechargeInvoiceStatus(invoice.getOrderNumberList().get(0));
        invoiceMapper.insert(dto);
        return ResponseBean.success("申请开票成功");
    }

    private void updateRechargeInvoiceStatus(String id) {
        NeoRechargeRecordExample example = new NeoRechargeRecordExample();
        example.createCriteria().andOrderNumberEqualTo(id);
        NeoRechargeRecord record = new NeoRechargeRecord();
        record.setInvoicingStatus(true);
        record.setUpdateId(commonService.fetchUserByMobile().getId());
        record.setUpdateDate(new Date());
        rechargeRecordMapper.updateByExampleSelective(record, example);
    }

    /**
     * 获取开票记录
     */
    public ResponseBean getInvoiceList() {
        NeoUser user = commonService.fetchUserByMobile();
        NeoInvoiceExample example = new NeoInvoiceExample();
        example.createCriteria().andCreatorIdEqualTo(user.getId());
        return ResponseBean.success(invoiceMapper.selectByExample(example));
    }

    /**
     * 获取完税凭证
     */
    public ResponseBean getReceipts() {
        NeoCompany company = commonService.fetchCurrentCompany();
        NeoCompanyTaxExample example = new NeoCompanyTaxExample();
        example.createCriteria().andCompanyIdEqualTo(company.getId());
        List<NeoCompanyTax> taxList = taxMapper.selectByExample(example);
        List<ITaxReceipt> newTaxList = new ArrayList<>();
        for (NeoCompanyTax tax: taxList) {
            ITaxReceipt receipt = new ITaxReceipt();
            BeanUtils.copyProperties(tax, receipt);
            receipt.setCompanyName(company.getCompanyName());
            newTaxList.add(receipt);
        }
        return ResponseBean.success(newTaxList);
    }


    /**
     * 公司财务操作
     * （operation =true  ->   充值）
     * （operation =false  ->   发放）
     * @param amount
     * @param operation
     * @return
     */
    public Boolean refreshFinance(NeoCompany company, Double amount, Boolean operation){
        NeoFinanceExample financeExample = new NeoFinanceExample();
        financeExample.createCriteria().andCompanyIdEqualTo(company.getId())
            .andStatusEqualTo(true);
        List<NeoFinance> finances = financeMapper.selectByExample(financeExample);
        // 新用户
        if (finances.size() == 0) {
            NeoFinance fin = new NeoFinance();
            fin.setCompanyId(company.getId());
            fin.setTotalRecharge(amount);
            fin.setRate(company.getCompanyRate());
            fin.setBalance(DoubleUtil.formatDouble(amount*(1-company.getCompanyRate()*0.01)));
            fin.setStatus(true);
            fin.setCreatorId(commonService.fetchUserByMobile().getId());
            fin.setCreateDate(new Date());
            financeMapper.insert(fin);
            return true;
        }
        if (finances.size()!=1){
            throw new NeoException("未找到当前公司相关财务信息");
        }

        // 更新 finance 表，逻辑变更老的状态，
        NeoFinance finance = finances.get(0);
        NeoFinance oldFiance = new NeoFinance();
        oldFiance.setStatus(false);
        oldFiance.setUpdateId(commonService.fetchUserByMobile().getId());
        oldFiance.setUpdateDate(new Date());
        financeMapper.updateByExampleSelective(oldFiance,financeExample);
        //充值
        if (operation){
            finance.setId(null);
            finance.setTotalRecharge(finance.getTotalRecharge()+amount);
            finance.setBalance(finance.getBalance()+ DoubleUtil.formatDouble(amount*(1-finance.getRate()*0.01)));
            finance.setCreatorId(oldFiance.getUpdateId());
            finance.setCreateDate(new Date());
        }else {
        //发放
            finance.setId(null);
            finance.setTotalIssued(finance.getTotalIssued()+amount);
            finance.setBalance(finance.getBalance()-amount);
            finance.setCreatorId(oldFiance.getUpdateId());
            finance.setCreateDate(new Date());
        }
        financeMapper.insertSelective(finance);
        return true;
    }
}

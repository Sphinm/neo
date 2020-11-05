package com.example.neo.service.Impl;

import com.example.neo.enums.ResponseCodeEnum;
import com.example.neo.exception.NeoException;
import com.example.neo.model.ICharge;
import com.example.neo.model.IChargeInfo;
import com.example.neo.model.ICompanyList;
import com.example.neo.model.IFianceInfo;
import com.example.neo.mybatis.mapper.NeoCompanyMapper;
import com.example.neo.mybatis.mapper.NeoFinanceMapper;
import com.example.neo.mybatis.mapper.NeoRechargeRecordMapper;
import com.example.neo.mybatis.mapper.NeoUserMapper;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

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
    private CommonService commonService;
    @Value("${neo.charge.filepath}")
    private String filePath;

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

    @Override
    public String getCompanyNameByCompanyId(int companyId) {
        NeoCompanyExample companyExample = new NeoCompanyExample();
        companyExample.createCriteria().andIdEqualTo(companyId);
        List<NeoCompany> companies = companyMapper.selectByExample(companyExample);
        if (companies==null||companies.size()!=1){
            return null;
        }
        return companies.get(0).getCompanyName();
    }

    @Override
    public ResponseBean charge(MultipartFile file, ICharge icharge) {
        Double amount = DoubleUtil.formatDouble(icharge.getAmount());
        if (file.isEmpty()) {
            return ResponseBean.fail(ResponseCodeEnum.FILE_NOT_NULL);
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        log.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        log.info("上传的后缀名为：" + suffixName);
        // 解决中文问题，liunx下中文路径，图片显示问题
        fileName = UUID.randomUUID() + suffixName;
        File file1 = new File(filePath);
        if  (!file1 .exists()  && !file1 .isDirectory())
        {
            log.info("{}不存在，创建目录",filePath);
            file1 .mkdir();
        } else {
            log.info("//目录存在");
        }
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        log.info(dest.getAbsolutePath());
        log.info(dest.getParentFile().getAbsolutePath());
        if (!dest.getParentFile().exists()) {
            log.info("目录不存在，创建目录");
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            log.info(e.getMessage());
            return ResponseBean.fail(ResponseCodeEnum.FILE_ERROR);
        } catch (IOException e) {
            log.info(e.getMessage());
            return ResponseBean.fail(ResponseCodeEnum.FILE_ERROR);
        }
        //获取当前关联公司信息
        NeoCompany company = commonService.fetchCurrentCompany();
        //公司税率默认为0
        float rate = getRate(company.getId());
        log.info("公司费率为{}",rate);
        if (!company.getCompanyType()){
            throw new NeoException("代理商不可进行充值操作");
        }
        NeoRechargeRecord rechargeRecord = new NeoRechargeRecord();
        rechargeRecord.setCompanyId(company.getId());
        //订单号使用雪花id
        rechargeRecord.setOrderNumber(String.valueOf(Snowflake.INSTANCE.nextId()));
        rechargeRecord.setPaymentAmount(amount);
        log.info("充值金额为{}",amount);
        rechargeRecord.setAccountAmount(DoubleUtil.formatDouble((1-rate)*icharge.getAmount()));
        log.info("到账金额为{}",DoubleUtil.formatDouble((1-rate)*icharge.getAmount()));
        rechargeRecord.setPaymentVoucher(fileName);
        rechargeRecord.setRate(rate);
        rechargeRecord.setInvoicingStatus(false);
        rechargeRecord.setApprovalStatus(false);
        rechargeRecord.setCreatorId(commonService.fetchUserByMobile().getId());
        rechargeRecord.setCreateDate(new Date());
        //添加充值记录
        rechargeRecordMapper.insert(rechargeRecord);
        //充值，更改财务表，余额和充值总额
        refreshFinance(company.getId(),icharge.getAmount(),true);
        return ResponseBean.success("充值成功");
    }

    @Override
    public ResponseBean getChargeInfo() {
        NeoCompany company = commonService.fetchCurrentCompany();
        NeoFinanceExample financeExample = new NeoFinanceExample();
        financeExample.createCriteria().andCompanyIdEqualTo(company.getId())
            .andStatusEqualTo(true);
        List<NeoFinance> finances = financeMapper.selectByExample(financeExample);
        if (finances==null||finances.size()!=1){
            throw new NeoException("未找到当前公司相关财务信息");
        }
        NeoFinance finance = finances.get(0);
        IFianceInfo fianceInfo = new IFianceInfo();
        BeanUtils.copyProperties(finance,fianceInfo);
        return ResponseBean.success(fianceInfo);
    }

    @Override
    public ResponseBean getChargeList(int pageNum,int pageSize) {
        NeoCompany company = commonService.fetchCurrentCompany();
        NeoRechargeRecordExample rechargeRecordExample = new NeoRechargeRecordExample();
        rechargeRecordExample.createCriteria().andCompanyIdEqualTo(company.getId());
        PageHelper.startPage(pageNum,pageSize);
        List<NeoRechargeRecord> records = rechargeRecordMapper.selectByExample(rechargeRecordExample);
        PageInfo<NeoRechargeRecord> pageInfo = new PageInfo<>(records);
        PageInfo<IChargeInfo> newPageInfo = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo,newPageInfo);
        List<NeoRechargeRecord> mid = pageInfo.getList();
        List<IChargeInfo> results = new ArrayList<>();
        if (mid==null||mid.size()==0) {
            return ResponseBean.success(newPageInfo);
        }
        for (NeoRechargeRecord record:mid){
            IChargeInfo chargeInfo = new IChargeInfo();
            BeanUtils.copyProperties(record,chargeInfo);
            chargeInfo.setCompanyName(getCompanyNameByCompanyId(record.getCompanyId()));
            results.add(chargeInfo);
        }
        newPageInfo.setList(results);
        return ResponseBean.success(newPageInfo);
    }

    /**
     * 查询公司费率
     * @param companyId
     * @return
     */
    private float getRate(Integer companyId){
        NeoFinanceExample financeExample = new NeoFinanceExample();
        financeExample.createCriteria().andCompanyIdEqualTo(companyId)
            .andStatusEqualTo(true);
        List<NeoFinance> finances = financeMapper.selectByExample(financeExample);
        if (finances==null||finances.size()!=1){
            throw new NeoException("未找到当前公司相关财务信息");
        }
        float rate = finances.get(0).getRate();
        if (rate<0){
            throw new NeoException("当前公司费率错误");
        }
        return rate;
    }

    /**
     * 公司财务操作
     * （operation =true  ->   充值）
     * （operation =false  ->   发放）
     * @param companyId
     * @param amount
     * @param operation
     * @return
     */
    public Boolean refreshFinance(Integer companyId,Double amount,boolean operation){
        NeoFinanceExample financeExample = new NeoFinanceExample();
        financeExample.createCriteria().andCompanyIdEqualTo(companyId)
            .andStatusEqualTo(true);
        List<NeoFinance> finances = financeMapper.selectByExample(financeExample);
        if (finances==null||finances.size()!=1){
            throw new NeoException("未找到当前公司相关财务信息");
        }
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
            finance.setBalance(finance.getBalance()+ DoubleUtil.formatDouble(amount*(1-finance.getRate())));
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

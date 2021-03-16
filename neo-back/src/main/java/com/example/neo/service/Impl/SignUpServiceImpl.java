package com.example.neo.service.Impl;

import com.example.neo.enums.ResponseCodeEnum;
import com.example.neo.model.IChangeMobile;
import com.example.neo.model.IProvideDate;
import com.example.neo.mybatis.mapper.NeoEmployeeMapper;
import com.example.neo.mybatis.mapper.NeoIssueDetailMapper;
import com.example.neo.mybatis.mapper.NeoIssueMapper;
import com.example.neo.mybatis.model.*;
import com.example.neo.service.SignUpService;
import com.example.neo.utils.DoubleUtil;
import com.example.neo.utils.IDCardCheck;
import com.example.neo.utils.ResponseBean;
import com.example.neo.utils.Snowflake;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class SignUpServiceImpl implements SignUpService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private NeoEmployeeMapper employeeMapper;
    @Autowired
    private NeoIssueMapper issueMapper;
    @Autowired
    private NeoIssueDetailMapper detailMapper;

    /**
     * 获取未签约的员工
     */
    @Override
    public ResponseBean fetchUnSignUpList() {
        NeoCompany company = commonService.fetchCurrentCompany();
        NeoEmployeeExample example = new NeoEmployeeExample();
        example.createCriteria().andCompanyIdEqualTo(company.getId()).andIsLockedEqualTo(false).andIsSignupEqualTo(false);
        return ResponseBean.success(employeeMapper.selectByExample(example));
    }

    /**
     * 获取签约的员工
     */
    @Override
    public ResponseBean fetchSignUpList() {
        NeoCompany company = commonService.fetchCurrentCompany();
        NeoEmployeeExample example = new NeoEmployeeExample();
        example.createCriteria().andCompanyIdEqualTo(company.getId()).andIsLockedEqualTo(false).andIsSignupEqualTo(true);
        return ResponseBean.success(employeeMapper.selectByExample(example));
    }

    /**
     * 根据身份证搜索
     *
     * @param id 身份证号
     */
    @Override
    public ResponseBean searchByIdCard(String id) {
        NeoCompany company = commonService.fetchCurrentCompany();
        NeoEmployeeExample example = new NeoEmployeeExample();
        example.createCriteria().andCompanyIdEqualTo(company.getId()).andIdVerifyEqualTo(id);
        List<NeoEmployee> list = employeeMapper.selectByExample(example);
        if (list.size() == 0) {
            return ResponseBean.fail(ResponseCodeEnum.NOT_FOUND);
        }
        return ResponseBean.success(list);
    }

    /**
     * 修改手机号
     */
    @Override
    public ResponseBean changeMobile(IChangeMobile mobile) {
        NeoCompany company = commonService.fetchCurrentCompany();
        NeoEmployeeExample example = new NeoEmployeeExample();
        example.createCriteria().andCompanyIdEqualTo(company.getId()).andTelEqualTo(mobile.getOldPhone());
        NeoEmployee newEmployee = new NeoEmployee();
        newEmployee.setTel(mobile.getNewPhone());
        employeeMapper.updateByExampleSelective(newEmployee, example);
        return ResponseBean.success();
    }

    /**
     * 发放相关逻辑
     * 上传发放列表
     *
     * @param file 发放文件
     */
    @Override
    public ResponseBean uploadProvideList(MultipartFile file, String taskName) {
        try {
            InputStream stream = file.getInputStream();
            Workbook wb;
            String fileName = file.getOriginalFilename();
            if (fileName != null && fileName.matches("^.+\\.(?i)(xlsx)$")) {
                wb = new XSSFWorkbook(stream);
            } else {
                wb = new HSSFWorkbook(stream);
            }
            Sheet sheet = wb.getSheetAt(0);
            if (sheet == null) {
                return ResponseBean.fail(ResponseCodeEnum.FILE_NOT_NULL);
            }

            // 列数
            int column = sheet.getRow(0).getPhysicalNumberOfCells();
            // 行数
            int rows = sheet.getLastRowNum();

            // 创建发放记录
            NeoCompany company = commonService.fetchCurrentCompany();
            NeoUser user = commonService.fetchUserByMobile();
            NeoIssue issue = new NeoIssue();
            String orderNumber = String.valueOf(Snowflake.INSTANCE.nextId());
            issue.setCompanyId(user.getRelatedId());
            issue.setOrderNumber(orderNumber);
            issue.setTaskName(taskName);
            issue.setStatus(false);
            issue.setCreatorId(user.getId());
            issue.setCreateDate(new Date());
            issue.setProvideStatus(false);
            issue.setIsDetele(false);

            // 创建发放详情
            List<NeoIssueDetail> list = new ArrayList<>();
            for (int i = 2; i <= rows; i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                NeoIssueDetail item = new NeoIssueDetail();
                item.setOrderNumber(orderNumber);
                // 打款流水号
                item.setBankSerialNumber(String.valueOf(Snowflake.INSTANCE.nextId()));
                item.setStatus(false);
                item.setCreatorId(user.getId());
                item.setCreateDate(new Date());

                // 用户名
                if (row.getCell(0) != null) {
                    String userName = row.getCell(0).getStringCellValue();
                    if (userName.isEmpty()) {
                        return ResponseBean.fail(ResponseCodeEnum.EXCEL_NAME_NOT_NULL);
                    }
                    item.setName(userName);
                }

                // 身份证
                // TODO 身份证校验有问题
                if (row.getCell(1) != null) {
                    String idCard = row.getCell(1).getStringCellValue();
//                    if (IDCardCheck.IDCardValidate(idCard)) {
                    item.setIdNumber(idCard);
//                    } else {
//                        return ResponseBean.fail(ResponseCodeEnum.ID_CARD_ERROR);
//                    }
                }

                // 银行卡号
                if (row.getCell(2) != null) {
                    String bankCode = row.getCell(2).getStringCellValue();
                    if (IDCardCheck.BankCardValidate(bankCode)) {
                        item.setBankNumber(bankCode);
                    } else {
                        return ResponseBean.fail(ResponseCodeEnum.BACK_CODE_ERROR);
                    }
                }

                // 手机号
                if (row.getCell(3) != null) {
                    String mobile = row.getCell(3).getStringCellValue();
                    if (IDCardCheck.isMobile(mobile)) {
                        item.setTel(mobile);
                    } else {
                        return ResponseBean.fail(ResponseCodeEnum.MOBILE_ERROR);
                    }
                }

                // 发放金额
                if (row.getCell(4) != null) {
                    String money = row.getCell(4).getStringCellValue();
                    item.setAmount(Double.parseDouble(money));
                }
                list.add(item);
                // 没有批量插入数据库的语法糖
                detailMapper.insert(item);
            }
            // 每笔的发放金额总额
            double sumAmount = 0.00;
            for (NeoIssueDetail it: list) {
                sumAmount += it.getAmount();
            }
            issue.setAmount(sumAmount);
            // 返佣金额：发放金额 * （公司费率 - 代理商费率）
            Double a = company.getCompanyRate() * 0.01;
            Double b = commonService.fetchCompanyInfoById(company.getId()).getCompanyRate() * 0.01;
            if (a - b <= 0) {
                return ResponseBean.fail(ResponseCodeEnum.COMPANY_RATE_ERROR);
            }
            issue.setRebate(DoubleUtil.formatDouble(sumAmount * (a - b)));

            // 插入数据库
            issueMapper.insert(issue);
        } catch (Exception e) {
            return ResponseBean.fail(ResponseCodeEnum.SAVE_DATA_ERROR);
        }
        return ResponseBean.success();
    }

    /**
     * 获取发放列表
     */
    @Override
    public ResponseBean fetchProvideList() {
        NeoCompany company = commonService.fetchCurrentCompany();
        NeoIssueExample example = new NeoIssueExample();
        example.createCriteria().andCompanyIdEqualTo(company.getId()).andIsDeteleEqualTo(false);
        return ResponseBean.success(issueMapper.selectByExample(example));
    }

    /**
     * 发放人员详情
     */
    @Override
    public ResponseBean fetchProvideDetail() {
        NeoUser user = commonService.fetchUserByMobile();
        NeoIssueDetailExample example = new NeoIssueDetailExample();
        example.createCriteria().andCreatorIdEqualTo(user.getId());
        return ResponseBean.success(detailMapper.selectByExample(example));
    }

    /**
     * 删除发放记录
     *
     * @param id
     */
    @Override
    public ResponseBean deleteProvideById(int id) {
        NeoIssueExample example = new NeoIssueExample();
        example.createCriteria().andIdEqualTo(id);
        NeoIssue issue = new NeoIssue();
        issue.setIsDetele(true);
        issueMapper.updateByExampleSelective(issue, example);
        return ResponseBean.success();
    }

    /**
     * 根据 idCard 搜索发放记录
     *
     * @param id
     */
    @Override
    public ResponseBean searchProvideByIdCard(String id) {
        return null;
    }

    /**
     * 根据时间区间搜索发放记录
     *
     * @param provideDate
     */
    @Override
    public ResponseBean searchProvideByDate(IProvideDate provideDate) {
        return null;
    }
}

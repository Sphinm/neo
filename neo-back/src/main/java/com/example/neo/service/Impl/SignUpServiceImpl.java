package com.example.neo.service.Impl;

import com.example.neo.enums.ResponseCodeEnum;
import com.example.neo.model.IChangeMobile;
import com.example.neo.mybatis.mapper.NeoEmployeeMapper;
import com.example.neo.mybatis.model.NeoCompany;
import com.example.neo.mybatis.model.NeoEmployee;
import com.example.neo.mybatis.model.NeoEmployeeExample;
import com.example.neo.mybatis.model.NeoIssueDetail;
import com.example.neo.service.SignUpService;
import com.example.neo.utils.ResponseBean;
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
import java.util.List;

@Slf4j
@Service
public class SignUpServiceImpl implements SignUpService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private NeoEmployeeMapper employeeMapper;

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
     * @param file
     */
    @Override
    public ResponseBean uploadProvideList(MultipartFile file) {
        try {
            InputStream stream = file.getInputStream();
            Workbook wb;
            String fileName = file.getOriginalFilename();
            if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
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

            List<NeoIssueDetail>  issueDetails = new ArrayList<>();

            for (int i = 3; i<=rows; i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                NeoIssueDetail item = new NeoIssueDetail();

                if (row.getCell(0) != null) {
//                    item
                }

            }

        } catch (Exception e) {
            return ResponseBean.fail(ResponseCodeEnum.SAVE_DATA_ERROR);
        }
        return ResponseBean.success();
    }
}

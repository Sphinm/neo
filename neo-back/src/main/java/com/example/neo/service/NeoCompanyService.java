package com.example.neo.service;

import com.example.neo.model.ICharge;
import com.example.neo.model.ICompanyList;
import com.example.neo.mybatis.model.NeoCompany;
import com.example.neo.utils.ResponseBean;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NeoCompanyService {
    List<ICompanyList> fetchCompanyList();

    String getCompanyNameByCompanyId(int companyId);
    /**
     * 充值操作
     * @param file
     * @param icharge
     * @return
     */
    ResponseBean charge(MultipartFile file, ICharge icharge);

    /**
     * 获取当前用户充值页面公司财务信息
     * @return
     */
    ResponseBean getChargeInfo();

    /**
     * 充值列表
     * @return
     */
    ResponseBean getChargeList(int pageNum,int pageSize);
}

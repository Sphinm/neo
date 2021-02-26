package com.example.neo.controller;

import com.example.neo.enums.UserTypeEnum;
import com.example.neo.model.ICreateUser;
import com.example.neo.model.IDataQuery;
import com.example.neo.model.IEmployee;
import com.example.neo.model.IGetUser;
import com.example.neo.mybatis.model.NeoCompany;
import com.example.neo.service.UserService;
import com.example.neo.utils.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PreAuthorize("hasAnyAuthority('user_view')")
    @GetMapping("/userInfo")
    public ResponseBean getUserInfo() {
        IGetUser user = userService.fetchUserInfo();
        return ResponseBean.success(user);
    }

    @PreAuthorize("hasAnyAuthority('user_view')")
    @PostMapping("/create/user")
    public ResponseBean createUser(@RequestBody ICreateUser user, @RequestParam("type") UserTypeEnum userType) {
        userService.createUser(user, userType);
        return ResponseBean.success();
    }

    @PreAuthorize("hasAnyAuthority('user_view')")
    @PostMapping("/insert/userInfo")
    public ResponseBean insertUserInfo(@RequestBody NeoCompany companyInfo, @RequestParam("type") UserTypeEnum userType) {
        userService.insertUserInfo(companyInfo, userType);
        return ResponseBean.success();
    }

    @PreAuthorize("hasAnyAuthority('user_view')")
    @PostMapping("/update/userInfo")
    public ResponseBean updateUserInfo(@RequestBody NeoCompany companyInfo) {
        return userService.updateUserInfo(companyInfo);
    }

    @PreAuthorize("hasAnyAuthority('user_merchant')")
    @GetMapping("/fetch/merchant")
    public ResponseBean fetchMerchantInfoByType(@RequestParam("type") UserTypeEnum userType) {
        List<ICreateUser> companyList = userService.fetchMerchantInfo(userType);
        return ResponseBean.success(companyList);
    }

    @PreAuthorize("hasAnyAuthority('user_merchant')")
    @PostMapping("/update/merchant")
    public ResponseBean updateMerchantInfoByType(@RequestBody ICreateUser user, @RequestParam("type") UserTypeEnum userType) {
        userService.updateMerchantInfo(user, userType);
        return ResponseBean.success();
    }

    @PreAuthorize("hasAnyAuthority('user_merchant')")
    @PostMapping("/delete/merchant")
    public ResponseBean deleteMerchantInfoByType(@RequestParam("merchantId") int merchantId) {
        userService.deleteMerchantInfo(merchantId);
        return ResponseBean.success();
    }

    @PreAuthorize("hasAnyAuthority('user_employee')")
    @GetMapping("/fetch/employee")
    public ResponseBean fetchEmployee() {
        List<IEmployee> employeeList = userService.fetchEmployee();
        return ResponseBean.success(employeeList);
    }

    @PreAuthorize("hasAnyAuthority('user_employee')")
    @PostMapping("/delete/employee")
    public ResponseBean deleteEmployee(@RequestParam("employeeId") int employeeId) {
        userService.deleteEmployee(employeeId);
        return ResponseBean.success();
    }

    @PreAuthorize("hasAnyAuthority('user_dataquery')")
    @GetMapping("/fetch/allData")
    public ResponseBean fetchDataQuery() {
        List<IDataQuery> dataQueryListList = userService.fetchDataQuery();
        return ResponseBean.success(dataQueryListList);
    }
}

package com.example.neo.controller;

import com.example.neo.enums.UserTypeEnum;
import com.example.neo.model.ICreateUser;
import com.example.neo.mybatis.model.NeoCompany;
import com.example.neo.service.UserService;
import com.example.neo.utils.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PreAuthorize("hasAnyAuthority('user_view')")
    @GetMapping("/userInfo")
    public ResponseBean getUserInfo() {
        return userService.fetchUserInfo();
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
        return userService.insertUserInfo(companyInfo, userType);
    }

    @PreAuthorize("hasAnyAuthority('user_view')")
    @PostMapping("/update/userInfo")
    public ResponseBean updateUserInfo(@RequestBody NeoCompany companyInfo) {
        return userService.updateUserInfo(companyInfo);
    }

    @PreAuthorize("hasAnyAuthority('user_merchant')")
    @GetMapping("/fetch/merchant")
    public ResponseBean fetchMerchantInfoByType(@RequestParam("type") UserTypeEnum userType) {
        return userService.fetchMerchantInfo(userType);
    }

    @PreAuthorize("hasAnyAuthority('user_merchant')")
    @PostMapping("/update/merchant")
    public ResponseBean updateMerchantInfoByType(@RequestBody ICreateUser user, @RequestParam("type") UserTypeEnum userType) {
        return userService.updateMerchantInfo(user, userType);
    }

    @PreAuthorize("hasAnyAuthority('user_merchant')")
    @PostMapping("/delete/merchant")
    public ResponseBean deleteMerchantInfoByType(@RequestParam("merchantId") int merchantId) {
        return userService.deleteMerchantInfo(merchantId);
    }

    @PreAuthorize("hasAnyAuthority('user_employee')")
    @GetMapping("/fetch/employee")
    public ResponseBean fetchEmployee() {
        return userService.fetchEmployee();
    }

    @PreAuthorize("hasAnyAuthority('user_employee')")
    @PostMapping("/delete/employee")
    public ResponseBean deleteEmployee(@RequestParam("employeeId") int employeeId) {
        return userService.deleteEmployee(employeeId);
    }

    @PreAuthorize("hasAnyAuthority('user_dataquery')")
    @GetMapping("/fetch/allData")
    public ResponseBean fetchDataQuery() {
        return userService.fetchDataQuery();
    }

    @PreAuthorize("hasAnyAuthority('user_dataquery')")
    @GetMapping("/fetch/company-list")
    public ResponseBean fetchDataQuery(@RequestParam("merchantId") int merchantId) {
        return userService.fetchCompanyListByMerchantId(merchantId);
    }
}

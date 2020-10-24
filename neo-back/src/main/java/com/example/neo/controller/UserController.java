package com.example.neo.controller;

import com.example.neo.enums.UserTypeEnum;
import com.example.neo.model.ICreateUser;
import com.example.neo.model.IGetUser;
import com.example.neo.mybatis.model.NoCompany;
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
    public ResponseBean insertUserInfo(@RequestBody NoCompany companyInfo, @RequestParam("type") UserTypeEnum userType) {
        userService.insertUserInfo(companyInfo, userType);
        return ResponseBean.success();
    }

    @PreAuthorize("hasAnyAuthority('user_view')")
    @PostMapping("/update/userInfo")
    public ResponseBean updateUserInfo(@RequestBody NoCompany companyInfo) {
        userService.updateUserInfo(companyInfo);
        return ResponseBean.success();
    }

}

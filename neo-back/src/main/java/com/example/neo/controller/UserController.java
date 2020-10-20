package com.example.neo.controller;

import com.example.neo.annotation.UserLoginToken;
import com.example.neo.entity.CompanyInfo;
import com.example.neo.enums.UserTypeEnum;
import com.example.neo.model.ICreateUser;
import com.example.neo.model.IGetUser;
import com.example.neo.service.UserService;
import com.example.neo.utils.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PreAuthorize("hasAnyAuthority('user_view')")
    @GetMapping("/userInfo")
    public ResponseBean getUserInfo() {
        String mobile = SecurityContextHolder.getContext().getAuthentication().getName();
        IGetUser user = userService.findByMobile(mobile);
        return ResponseBean.success(user);
    }


    @UserLoginToken
    @PostMapping("/create/user")
    public ResponseBean createUser(@RequestBody ICreateUser user, @RequestParam("type") UserTypeEnum userType) {
        userService.createUser(user, userType);
        return ResponseBean.success(user);
    }

    @UserLoginToken
    @PostMapping("/insert/userInfo")
    public ResponseBean insertUserInfo(@RequestBody CompanyInfo companyInfo, @RequestParam("type") UserTypeEnum userType) {
        userService.insertUserInfo(companyInfo, userType);
        return ResponseBean.success();
    }

    @UserLoginToken
    @PostMapping("/update/userInfo")
    public ResponseBean updateUserInfo(@RequestBody CompanyInfo companyInfo) {
        userService.updateUserInfo(companyInfo);
        return ResponseBean.success(companyInfo);
    }

}

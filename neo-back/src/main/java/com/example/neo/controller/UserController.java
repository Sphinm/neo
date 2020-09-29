package com.example.neo.controller;

import com.example.neo.annotation.UserLoginToken;
import com.example.neo.entity.CompanyInfo;
import com.example.neo.enums.UserTypeEnum;
import com.example.neo.model.ICreateUser;
import com.example.neo.service.UserService;
import com.example.neo.utils.ContextHolder;
import com.example.neo.utils.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @UserLoginToken
    @PostMapping("/create/user")
    public ResponseBean createUser(@RequestBody ICreateUser user, @RequestParam("type") UserTypeEnum userType) {
        userService.createUser(user, userType);
        return ResponseBean.success(user);
    }

    @UserLoginToken
    @GetMapping("/get/userInfo")
    public ResponseBean fetchUserInfo() {
        String userId = ContextHolder.getCurrentUserId();
        CompanyInfo companyInfo = userService.fetchUserInfo(userId);
        return ResponseBean.success(companyInfo);
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

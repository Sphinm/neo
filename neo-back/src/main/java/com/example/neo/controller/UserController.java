package com.example.neo.controller;

import com.example.neo.annotation.UserLoginToken;
import com.example.neo.entity.params.ICreateUser;
import com.example.neo.enums.UserTypeEnum;
import com.example.neo.model.UserInfo;
import com.example.neo.service.UserService;
import com.example.neo.utils.ContextHolder;
import com.example.neo.utils.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @UserLoginToken
    @PostMapping("/create/user")
    public ResponseBean createUser(@RequestBody ICreateUser user, UserTypeEnum userType) {
        userService.createUser(user, userType);
        return ResponseBean.success();
    }

    @UserLoginToken
    @GetMapping("/get/userInfo")
    public ResponseBean fetchUserInfo() {
        String userId = ContextHolder.getCurrentUserId();
        UserInfo userInfo = userService.fetchUserInfo(userId);
        return ResponseBean.success(userInfo);
    }

    @UserLoginToken
    @PostMapping("/insert/userInfo")
    public ResponseBean insertUserInfo(@RequestBody UserInfo userInfo) {
        userService.insertUserInfo(userInfo);
        return ResponseBean.success();
    }

    @UserLoginToken
    @PostMapping("/update/userInfo")
    public ResponseBean updateUserInfo(@RequestBody UserInfo userInfo) {
        UserInfo info = userService.updateUserInfo(userInfo);
        return ResponseBean.success(info);
    }

}

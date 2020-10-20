package com.example.neo.controller;

import com.example.neo.annotation.UserLoginToken;
import com.example.neo.entity.User;
import com.example.neo.enums.ResponseCodeEnum;
import com.example.neo.model.IChangePassword;
import com.example.neo.model.ILogin;
import com.example.neo.service.AuthService;
import com.example.neo.service.UserService;
import com.example.neo.utils.ContextHolder;
import com.example.neo.utils.ResponseBean;
import com.example.neo.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthController {
    @Autowired
    AuthService AuthService;

    @Autowired
    UserService userService;

    @Autowired
    TokenUtils TokenUtils;

    @PostMapping("/login")
    public ResponseBean login(@RequestBody ILogin login) {
        User userInfo = AuthService.login(login.getUserName());
        log.info("userInfo ===> {}", userInfo);
        if (userInfo == null) {
            return ResponseBean.fail(ResponseCodeEnum.USER_NOTFOUND);
        }
        if (!userInfo.getPassword().equals(login.getPassword())) {
            return ResponseBean.fail(ResponseCodeEnum.PASSWORD_ERROR);
        } else {
            return ResponseBean.success(userInfo);
        }
    }

    @PostMapping("/newLogin")
    public ResponseBean newLogin(@RequestBody ILogin login) {
        return AuthService.newLogin(login);
    }

    @PostMapping("/logout")
    public void logout() {
        AuthService.logout();
    }

    @UserLoginToken
    @PostMapping("/change/password")
    public ResponseBean changePassword(@RequestBody IChangePassword pwd) {
        String userId = ContextHolder.getCurrentUserId();
        ResponseCodeEnum code = AuthService.changePwd(pwd, userId);
        if (code == ResponseCodeEnum.INIT_PASSWORD_ERROR) {
            return ResponseBean.fail(ResponseCodeEnum.INIT_PASSWORD_ERROR);
        }
        if (code == ResponseCodeEnum.PASSWORD_EQUALS) {
            return ResponseBean.fail(ResponseCodeEnum.PASSWORD_EQUALS);
        }
        return ResponseBean.success();
    }

}

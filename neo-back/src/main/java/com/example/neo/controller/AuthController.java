package com.example.neo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.neo.constant.Constants;
import com.example.neo.entity.params.ILogin;
import com.example.neo.enums.ResponseCodeEnum;
import com.example.neo.model.User;
import com.example.neo.service.AuthService;
import com.example.neo.service.TokenService;
import com.example.neo.utils.CookieUtils;
import com.example.neo.utils.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthService AuthService;

    @Autowired
    TokenService TokenService;

    @PostMapping("/login")
    public ResponseBean login(ILogin login) {
        JSONObject jsonObject = new JSONObject();
        User userInfo = AuthService.login(login.getMobile());

        if (userInfo == null) {
            return ResponseBean.fail(ResponseCodeEnum.USER_NOTFOUND);
        }
        if (!userInfo.getPassword().equals(login.getPassword())) {
            return ResponseBean.fail(ResponseCodeEnum.PASSWORD_ERROR);
        } else {
            String token = TokenService.getToken(userInfo);
            jsonObject.put("token", token);
            jsonObject.put("userInfo", userInfo);
            CookieUtils.setRaw(Constants.TOKEN_KEY, token, true);
            return ResponseBean.success(jsonObject);
        }
    }

    @PostMapping("/logout")
    ResponseBean logout() {
        AuthService.logout();
        return ResponseBean.success();
    }

}

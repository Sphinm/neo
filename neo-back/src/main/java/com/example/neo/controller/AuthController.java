package com.example.neo.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.example.neo.annotation.UserLoginToken;
import com.example.neo.constant.Constants;
import com.example.neo.entity.params.ILogin;
import com.example.neo.enums.ResponseCodeEnum;
import com.example.neo.model.User;
import com.example.neo.service.AuthService;
import com.example.neo.utils.CookieUtils;
import com.example.neo.utils.ResponseBean;
import com.example.neo.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthController {
    @Autowired
    AuthService AuthService;

    @Autowired
    TokenUtils TokenUtils;

    @UserLoginToken
    @GetMapping("/me")
    public ResponseBean getUserInfo()  {
        String token = CookieUtils.getRaw(Constants.TOKEN_KEY);
        log.debug("user token11111111111: ", token);
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException e) {
            throw new RuntimeException("401, ", e);
        }

        User user = AuthService.findByUserId(userId);
        return ResponseBean.success(user);
    }

    @PostMapping("/login")
    public ResponseBean login(@RequestBody ILogin login) {
        User userInfo = AuthService.login(login.getMobile());

        if (userInfo == null) {
            return ResponseBean.fail(ResponseCodeEnum.USER_NOTFOUND);
        }
        if (!userInfo.getPassword().equals(login.getPassword())) {
            return ResponseBean.fail(ResponseCodeEnum.PASSWORD_ERROR);
        } else {
            String token = TokenUtils.getToken(userInfo);
            CookieUtils.setRaw(Constants.TOKEN_KEY, token, true);
            return ResponseBean.success(userInfo);
        }
    }

    @PostMapping("/logout")
    public ResponseBean logout() {
        AuthService.logout();
        return ResponseBean.success();
    }

}

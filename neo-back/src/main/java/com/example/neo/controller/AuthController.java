package com.example.neo.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.example.neo.annotation.UserLoginToken;
import com.example.neo.constant.Constants;
import com.example.neo.entity.params.IChangePassword;
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

    /**
     * 从 token 中获取用户信息
     * @return
     */
    private String fetchUserId() {
        String token = CookieUtils.getRaw(Constants.TOKEN_KEY);
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException e) {
            throw new RuntimeException("401, ", e);
        }
        return userId;
    }

    @UserLoginToken
    @GetMapping("/me")
    public ResponseBean getUserInfo()  {
        User user = AuthService.findByUserId(fetchUserId());
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
    public void logout() {
        AuthService.logout();
    }

    @UserLoginToken
    @PostMapping("/change/password")
    public ResponseBean changePassword(@RequestBody IChangePassword pwd) {
        ResponseCodeEnum code = AuthService.changePwd(pwd, fetchUserId());
        if (code == ResponseCodeEnum.INIT_PASSWORD_ERROR) {
            return ResponseBean.fail(ResponseCodeEnum.INIT_PASSWORD_ERROR);
        }
        if (code == ResponseCodeEnum.PASSWORD_EQUALS) {
            return ResponseBean.fail(ResponseCodeEnum.PASSWORD_EQUALS);
        }
        return ResponseBean.success();
    }

}

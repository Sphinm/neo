package com.example.neo.controller;

import com.example.neo.enums.ResponseCodeEnum;
import com.example.neo.model.IChangePassword;
import com.example.neo.model.ILogin;
import com.example.neo.service.AuthService;
import com.example.neo.service.UserService;
import com.example.neo.utils.ContextHolder;
import com.example.neo.utils.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class AuthController {
    @Autowired
    AuthService AuthService;

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/newLogin")
    public ResponseBean newLogin(@RequestBody ILogin login) {
        return AuthService.newLogin(login);
    }

    @PostMapping("/logout")
    public void logout() {
        AuthService.logout();
    }

    @PreAuthorize("hasAnyAuthority('changePassword')")
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

    @PreAuthorize("hasAnyAuthority('pass')")
    @PostMapping("/encodePassword")
    public ResponseBean encodePassword(@RequestBody Map<String,Object> map){
        return ResponseBean.success(passwordEncoder.encode((CharSequence) map.get("password")));
    }

}

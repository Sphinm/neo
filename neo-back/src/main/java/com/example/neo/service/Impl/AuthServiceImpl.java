package com.example.neo.service.Impl;

import com.example.neo.constant.Constants;
import com.example.neo.entity.params.IChangePassword;
import com.example.neo.enums.ResponseCodeEnum;
import com.example.neo.mapper.UserMapper;
import com.example.neo.model.User;
import com.example.neo.service.AuthService;
import com.example.neo.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Autowired
    UserMapper UserMapper;

    public User login(String mobile) {
        return UserMapper.findByUserMobile(mobile);
    }

    public void logout() {
        CookieUtils.clean(Constants.TOKEN_KEY);
    }

    public ResponseCodeEnum changePwd(IChangePassword pwd, String userId) {
        User userInfo = UserMapper.findByUserId(userId);
        if (!pwd.getOldPwd().equals(userInfo.getPassword())) {
            return ResponseCodeEnum.INIT_PASSWORD_ERROR;
        }
        if (pwd.getOldPwd().equals(pwd.getNewPwd())) {
            return ResponseCodeEnum.PASSWORD_EQUALS;
        }
        UserMapper.changePassword(pwd, userId);
        return ResponseCodeEnum.SUCCESS;
    }
}

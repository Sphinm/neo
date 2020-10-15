package com.example.neo.service.Impl;

import com.example.neo.constant.Constants;
import com.example.neo.model.IChangePassword;
import com.example.neo.enums.ResponseCodeEnum;
import com.example.neo.mapper.UserMapper;
import com.example.neo.entity.User;
import com.example.neo.model.ILogin;
import com.example.neo.mybatis.model.NeoUser;
import com.example.neo.mybatis.model.NeoUserExample;
import com.example.neo.mybatis.model.mapper.NeoUserMapper;
import com.example.neo.security.WebSecurityConfiguration;
import com.example.neo.service.AuthService;
import com.example.neo.utils.CookieUtils;
import com.example.neo.utils.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Autowired
    UserMapper UserMapper;
    @Autowired
    private NeoUserMapper neoUserMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @Override
    public ResponseBean newLogin(ILogin iLogin) {

        return null;
    }

    public boolean checkPassword(ILogin iLogin){
        NeoUserExample neoUserExample = new NeoUserExample();
        NeoUserExample.Criteria c1= neoUserExample.createCriteria().andAccountEqualTo(iLogin.getUserName());
        NeoUserExample.Criteria c2= neoUserExample.createCriteria().andEmailEqualTo(iLogin.getUserName());
        neoUserExample.or(c2);
        List<NeoUser> results = neoUserMapper.selectByExample(neoUserExample);
        if (results==null||results.size()==0){
            //抛异常
        }
        //
        return passwordEncoder.matches(iLogin.getPassword(),results.get(0).getPassword());
    }
}

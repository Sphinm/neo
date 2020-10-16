package com.example.neo.service.Impl;

import com.example.neo.constant.Constants;
import com.example.neo.exception.NeoException;
import com.example.neo.model.IChangePassword;
import com.example.neo.enums.ResponseCodeEnum;
import com.example.neo.mapper.UserMapper;
import com.example.neo.entity.User;
import com.example.neo.model.ILogin;
import com.example.neo.mybatis.model.NeoUser;
import com.example.neo.mybatis.model.NeoUserExample;
import com.example.neo.mybatis.model.mapper.NeoUserMapper;
import com.example.neo.security.JwtTokenUtil;
import com.example.neo.security.WebSecurityConfiguration;
import com.example.neo.service.AuthService;
import com.example.neo.utils.CookieUtils;
import com.example.neo.utils.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@PropertySource({"classpath:application.yml"})
public class AuthServiceImpl implements AuthService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private UserMapper UserMapper;
    @Autowired
    private NeoUserMapper neoUserMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${neo.token.expired-seconds}")
    public Long expiredTime;
    @Value("${neo.token.secret-key}")
    public String secretKey;

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
        //首先判断用户是否存在，密码是否正确
        NeoUser user = checkPassword(iLogin);
        if (user==null){
            return ResponseBean.fail(ResponseCodeEnum.LOGIN_FAILED);
        }
        //生成token，放入redis
        String token = JwtTokenUtil.generatorJwtToken(user.getId(),user.getUsername(),expiredTime,secretKey);
        log.info("token = {}",token);
        redisTemplate.opsForValue().set(token,user.getMobile(),expiredTime);
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        return ResponseBean.success(tokenMap);
    }

    public NeoUser checkPassword(ILogin iLogin){
        NeoUserExample neoUserExample = new NeoUserExample();
        NeoUserExample.Criteria c1= neoUserExample.createCriteria().andAccountEqualTo(iLogin.getUserName());
        NeoUserExample.Criteria c2= neoUserExample.createCriteria().andEmailEqualTo(iLogin.getUserName());
        neoUserExample.or(c2);
        List<NeoUser> results = neoUserMapper.selectByExample(neoUserExample);
        if (results==null||results.size()!=0){
            return null;
        }
        if (passwordEncoder.matches(iLogin.getPassword(),results.get(0).getPassword())){
            return results.get(0);
        }
        return null;
    }
}

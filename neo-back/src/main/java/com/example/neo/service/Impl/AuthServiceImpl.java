package com.example.neo.service.Impl;

import com.example.neo.enums.ResponseCodeEnum;
import com.example.neo.model.IChangePassword;
import com.example.neo.model.ILogin;
import com.example.neo.mybatis.mapper.NeoUserMapper;
import com.example.neo.mybatis.model.NeoUser;
import com.example.neo.mybatis.model.NeoUserExample;
import com.example.neo.security.JwtTokenUtil;
import com.example.neo.service.AuthService;
import com.example.neo.utils.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@PropertySource({"classpath:application.yml"})
public class AuthServiceImpl implements AuthService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private NeoUserMapper neoUserMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CommonService commonService;
    @Value("${neo.token.expired-seconds}")
    public Long expiredTime;
    @Value("${neo.token.secret-key}")
    public String secretKey;

    public ResponseBean logout() {
        NeoUser user = commonService.fetchUserByMobile();
        String token = JwtTokenUtil.generatorJwtToken(user.getId(), user.getUsername(), expiredTime, secretKey);
        return ResponseBean.success(redisTemplate.delete(token));
    }

    public ResponseBean changePwd(IChangePassword pwd) {
        NeoUser user = new NeoUser();
        NeoUser userInfo = commonService.fetchUserByMobile();
        if (!passwordEncoder.matches(pwd.getOldPwd(), userInfo.getPassword())) {
            return ResponseBean.fail(ResponseCodeEnum.INIT_PASSWORD_ERROR);
        }
        if (passwordEncoder.matches(pwd.getOldPwd(), pwd.getNewPwd())) {
            return ResponseBean.fail(ResponseCodeEnum.PASSWORD_EQUALS);
        }
        user.setId(userInfo.getId());
        user.setPassword(passwordEncoder.encode(pwd.getNewPwd()));
        neoUserMapper.updateByPrimaryKeySelective(user);
        return ResponseBean.success();
    }

    @Override
    public ResponseBean newLogin(ILogin iLogin) {
        //首先判断用户是否存在，密码是否正确
        NeoUser user = checkPassword(iLogin);
        if (user == null) {
            return ResponseBean.fail(ResponseCodeEnum.LOGIN_FAILED);
        }
        //生成token，放入redis
        String token = JwtTokenUtil.generatorJwtToken(user.getId(), user.getUsername(), expiredTime, secretKey);
        redisTemplate.opsForValue().set(token, user.getMobile(), expiredTime, TimeUnit.SECONDS);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        return ResponseBean.success(tokenMap);
    }

    private NeoUser checkPassword(ILogin iLogin) {
        NeoUserExample neoUserExample = new NeoUserExample();
        neoUserExample.createCriteria().andMobileEqualTo(iLogin.getUserName());
        List<NeoUser> results = neoUserMapper.selectByExample(neoUserExample);
        if (results == null || results.size() != 1) {
            return null;
        }
        if (passwordEncoder.matches(iLogin.getPassword(), results.get(0).getPassword())) {
            return results.get(0);
        }
        return null;
    }
}

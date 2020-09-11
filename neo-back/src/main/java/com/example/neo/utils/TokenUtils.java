package com.example.neo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.neo.constant.Constants;
import com.example.neo.model.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("TokenService")
public class TokenUtils {
    public String getToken(User user) {
        Date expiredDate = new Date(System.currentTimeMillis() + Constants.TOKEN_EXPIRE_TIME);
        return JWT.create().withAudience(user.getUserId()).withExpiresAt(expiredDate).sign(Algorithm.HMAC512(Constants.JWT_SECRET));
    }
}
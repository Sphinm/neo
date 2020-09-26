package com.example.neo.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.neo.annotation.PassToken;
import com.example.neo.annotation.UserLoginToken;
import com.example.neo.constant.Constants;
import com.example.neo.model.IGetUser;
import com.example.neo.service.UserService;
import com.example.neo.utils.ContextHolder;
import com.example.neo.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * JWT 拦截器
 */
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
//        String cookies = httpServletRequest.getHeader("Cookie"); // 从 http 请求头获取 token
        String token = CookieUtils.getRaw(Constants.TOKEN_KEY);
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();

        // 检查是否有 passToken 注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        // 检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                if (token == null) {
                    throw new RuntimeException("用户信息缺失，请重新登录");
                }
                // 获取 token 中的 userId
                String userId;
                long expiredDate;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                    expiredDate = JWT.decode(token).getExpiresAt().getTime();

                } catch (JWTDecodeException e) {
                    throw new Exception("401, ", e);
                }
                if (expiredDate <= new Date().getTime()) {
                    CookieUtils.clean(Constants.TOKEN_KEY);
                    throw new Exception("token 过期，请重新登录");
                }
                ContextHolder.UserContext contextHolder = new ContextHolder.UserContext(userId);
                ContextHolder.setContext(contextHolder);
                IGetUser user = userService.findByUserId(userId);
                if (user == null) {
                    throw new Exception("用户不存在，请重新登录");
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC512(Constants.JWT_SECRET)).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new Exception("401：token 解析失败, ", e);
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {}

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {}
}

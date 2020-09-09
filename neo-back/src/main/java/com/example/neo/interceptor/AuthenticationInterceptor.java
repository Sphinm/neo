package com.example.neo.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.neo.annotation.PassToken;
import com.example.neo.annotation.UserLoginToken;
import com.example.neo.constant.Constants;
import com.example.neo.model.User;
import com.example.neo.service.AuthService;
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
 * JWT ������
 */
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    AuthService AuthService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
//        String cookies = httpServletRequest.getHeader("Cookie"); // �� http ����ͷ��ȡ token
        String token = CookieUtils.getRaw(Constants.TOKEN_KEY);
        // �������ӳ�䵽����ֱ��ͨ��
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();

        // ����Ƿ��� passToken ע�ͣ�����������֤
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        // �����û����Ҫ�û�Ȩ�޵�ע��
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                if (token == null) {
                    throw new RuntimeException("�û���Ϣȱʧ�������µ�¼");
                }
                // ��ȡ token �е� userId
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
                    throw new Exception("token ���ڣ������µ�¼");
                }

                User user = AuthService.findByUserId(userId);
                if (user == null) {
                    throw new Exception("�û������ڣ������µ�¼");
                }
                // ��֤ token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC512(Constants.JWT_SECRET)).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new Exception("401��token ����ʧ��, ", e);
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

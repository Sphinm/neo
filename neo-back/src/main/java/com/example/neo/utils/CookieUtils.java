package com.example.neo.utils;

import com.example.neo.constant.Constants;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Optional;

public class CookieUtils {

    /**
     * 向cookie存储新字段（使用 URL Encode 编码）
     */
    private static void setEncoded(String name, String content, boolean isHttpOnly) {
        try {
            setRaw(name, URLEncoder.encode(content, "UTF-8"), isHttpOnly);
        } catch (UnsupportedEncodingException ignored) {
        }
    }

    /**
     * 如果key != null,清除指定key的cookie,如果key为null,清除所有cookie
     */
    public static void clean(String key) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            if (!StringUtils.isEmpty(key)) {
                setRaw(key, "", true);
            } else {
                for (Cookie it: cookies) {
                    setEncoded(it.getName(), "", true);
                }
            }
        }
    }

    /**
     * 向cookie存储新字段（不做编解码处理）
     */
    public static void setRaw(String name, String value, boolean isHttpOnly) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            Cookie cookie = new Cookie(name, value);
            cookie.setPath("/");
            cookie.setMaxAge(Constants.COOKIES_EXPIRE_TIME);
            cookie.setHttpOnly(isHttpOnly);
            attributes.getResponse().addCookie(cookie);
        }
    }

    /**
     * 从cookie中取字段（不做编解码处理）
     */
    public static String getRaw(String name) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            Cookie[] cookies = attributes.getRequest().getCookies();
            if (cookies != null) {
                Optional<Cookie> first = Arrays.stream(cookies).filter(e -> name.equalsIgnoreCase(e.getName())).findFirst();
                if (first.isPresent()) {
                    return first.get().getValue();
                }
            }
        }
        return null;
    }
}

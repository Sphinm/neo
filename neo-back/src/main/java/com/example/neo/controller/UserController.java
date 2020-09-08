package com.example.neo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.neo.annotation.UserLoginToken;
import com.example.neo.model.User;
import com.example.neo.service.TokenService;
import com.example.neo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService UserService;

    @Autowired
    TokenService TokenService;

    @PostMapping("/login")
    public Object login(User user) {
        JSONObject jsonObject = new JSONObject();
        User userInfo = UserService.findByUserMobile(user);
        System.out.println(userInfo);
        if (userInfo == null) {
            System.out.println("��¼ʧ�� �û�������");
            jsonObject.put("message", "��¼ʧ�ܣ��û�������");
            return jsonObject;
        }
        if (!userInfo.getPassword().equals(user.getPassword())) {
            jsonObject.put("message", "��¼ʧ�ܣ��������");
            return jsonObject;
        } else {
            String token = TokenService.getToken(userInfo);
            System.out.println(token);
            jsonObject.put("token", token);
            jsonObject.put("user", userInfo);
            return jsonObject;
        }
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "����ͨ����֤";
    }
}

package com.example.neo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;

public class MessageUtil {
    private static final String ACCESS_KEY_Id = "AsccessKeyID";
    private static final String ACCESS_KEY_SECRET = "AsccessKeySecret";
    private static final String REGION_ID = "cn-hangzhou";
    private static final String SIGN_NAME = "短信签名";
    private static final String TEMPLATE_CODE = "短信模板code";


    public static void main(String[] args) {
        System.out.println(senMassage("phoneNum"));
    }

    public static JSONObject senMassage(String phoneNumber) {
        JSONObject result = new JSONObject();
        //随机生成6位验证码
        String code = String.valueOf((int) (Math.random() * 10000 + 10000)).substring(1);
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESS_KEY_Id, ACCESS_KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.putQueryParameter("RegionId", REGION_ID);
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", SIGN_NAME);
        request.putQueryParameter("TemplateCode", TEMPLATE_CODE);
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            result = JSON.parseObject(response.getData());
            result.put("verCode", code);
            return result;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return result;
    }
}

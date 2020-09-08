package com.example.neo.interceptor;

import com.example.neo.enums.ResponseCodeEnum;
import com.example.neo.utils.ResponseBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GloablExceptionHandler {
    @ResponseBody
    @ExceptionHandler({Exception.class, RuntimeException.class})
    public Object handleException(Exception e) {
        String msg = e.getMessage();
        if (msg == null || msg.equals("")) {
            msg = "·þÎñÆ÷³ö´í";
        }
        return ResponseBean.fail(ResponseCodeEnum.SERVER_ERROR, msg);
    }
}

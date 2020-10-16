package com.example.neo.interceptor;

import com.example.neo.enums.ResponseCodeEnum;
import com.example.neo.utils.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GloablExceptionHandler {
    @ResponseBody
    @ExceptionHandler({Exception.class, RuntimeException.class})
    public Object handleException(Exception e) {
        String msg = e.getMessage();
        log.info(e.toString());
        if (msg == null || msg.equals("")) {
            msg = "Server Error!";
        }
        return ResponseBean.fail(ResponseCodeEnum.SERVER_ERROR, msg);
    }
}

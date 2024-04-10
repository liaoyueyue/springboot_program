package com.example.danmudemo.config;

import cn.dev33.satoken.exception.NotLoginException;
import com.example.danmudemo.common.AjaxResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-04-10
 * Time: 10:38
 */
@ControllerAdvice
public class ErrorAdvice {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object exceptionAdvice(Exception e) {
        return AjaxResult.fail();
    }

    @ExceptionHandler(NotLoginException.class)
    @ResponseBody
    public Object exceptionAdvice(NotLoginException e) {
        return AjaxResult.fail("没有登录");
    }
}

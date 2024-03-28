package com.example.orderdemo.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.example.orderdemo.common.Result;
import com.example.orderdemo.service.UserService;
import com.example.orderdemo.utils.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-03-07
 * Time: 9:10
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Result login(HttpServletRequest req, HttpServletResponse resp, String username, String password, String captcha, String remember) {
        // 1.验证码判断
        String code = (String) req.getSession().getAttribute("vcode");
        if (!code.equalsIgnoreCase(captcha)) {
            return Result.fail("captcha error");
        }
        // 2.是否记住状态
        boolean isRemember = remember != null;
        if (!isRemember) {
            CookieUtil.deleteCookie(req, resp, "USER_INFO");
        }
        Result result = userService.login(username, password);
        if (result.getCode() == 0) {
            if (isRemember) {
                CookieUtil.setCookie(req, resp, "USER_INFO", String.format("%s:%s", username, password), 60 * 60 * 24 * 7);
            }
        }
        return result;
    }

    @GetMapping("/captcha")
    public void captcha(HttpServletRequest req, HttpServletResponse resp) {
        LineCaptcha lc = CaptchaUtil.createLineCaptcha(120, 40, 4, 20);
        String code = lc.getCode();
        req.getSession().setAttribute("vcode", code);
        try {
            lc.write(resp.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/setCookie")
    public void setCookie(HttpServletRequest req, HttpServletResponse resp) {
        Cookie cookie = new Cookie("username", "haha");
        cookie.setMaxAge(60 * 60 * 24 * 7);
        resp.addCookie(cookie);
    }

    @GetMapping("/getCookie")
    public String getCookie(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            return cookie.getName() + " | " + cookie.getValue();
        }
        return null;
    }

}

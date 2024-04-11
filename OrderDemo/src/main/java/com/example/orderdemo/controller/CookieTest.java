package com.example.orderdemo.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-04-11
 * Time: 9:36
 */
public class CookieTest {
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

package org.example.orderdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-03-07
 * Time: 9:10
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(String username, String password, String captcha) {
        System.out.println("登录成功" + username + "|" + password + "|" + captcha);
        return "登录成功" + username + "|" + password + "|" + captcha;
    }
}

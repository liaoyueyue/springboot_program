package org.example.myojssm.controller;

import org.example.myojssm.common.AjaxResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-03-10
 * Time: 11:47
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping("/login")
    public AjaxResult login(String username, String password, String captcha) {
        System.out.println(username + " | " + password + " | " + captcha);
        return AjaxResult.success(username + " | " + password + " | " + captcha);
    }

    @PostMapping("/register")
    public AjaxResult register(String nickname, String password) {
        System.out.println(nickname + " | " + password);
        return AjaxResult.success(nickname + " | " + password);
    }
}

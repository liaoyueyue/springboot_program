package org.example.myojssm.controller;

import org.example.myojssm.common.AjaxResult;
import org.example.myojssm.entity.User;
import org.example.myojssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public AjaxResult login(String username, String password, String captcha) {
        // 1.非空校验
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(password) || !StringUtils.hasLength(captcha)) {
            return AjaxResult.fail(-1, "illegal request");
        }
        // 2.判断验证码 --这里暂时做简单判断
        if (!captcha.equals("abcd")) {
            return AjaxResult.fail(-1, "captcha error");
        }
        // 3.判断用户有效和密码是否正确
        User user = userService.queryOneByUsername(username);
        if (user != null && user.getId() > 0) {
            // 有效用户判断密码
            if (user.getPassword().equals(password)) {
                user.setPassword("");
                return AjaxResult.success(user);
            }
        }
        return AjaxResult.fail(-1, "illegal username or password");
    }

    @PostMapping("/register")
    public AjaxResult register(String nickname, String password) {
        System.out.println(nickname + " | " + password);
        return AjaxResult.success(nickname + " | " + password);
    }
}

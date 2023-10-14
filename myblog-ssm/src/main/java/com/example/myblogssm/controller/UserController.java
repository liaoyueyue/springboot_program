package com.example.myblogssm.controller;

import com.example.myblogssm.common.AjaxResult;
import com.example.myblogssm.entity.User;
import com.example.myblogssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-10-11
 * Time: 17:58
 */
@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public AjaxResult register(User user) {
        // 非空校验
        if (user == null || !StringUtils.hasLength(user.getUsername()) || !StringUtils.hasLength(user.getPassword())) {
            return AjaxResult.fail(-1, "username or password not found");
        }
        return AjaxResult.success(userService.addUser(user));
    }

    @PostMapping("/login")
    public AjaxResult login(String username, String password) {
        // 非空校验
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(password)) {
            return AjaxResult.fail(-1, "illegal request");
        }
        User user = userService.queryUserByName(username);
        if (user != null && user.getId() > 0) {
            // 有效的用户, 判断密码
            if (password.equals(user.getPassword())) {
                user.setPassword("");
                return AjaxResult.success(user);
            }
        }
        // 用户为空或者密码错误
        return AjaxResult.fail(-1, "username or password not found");
    }

}

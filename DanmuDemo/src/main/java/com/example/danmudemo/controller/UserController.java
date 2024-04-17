package com.example.danmudemo.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.example.danmudemo.common.AjaxResult;
import com.example.danmudemo.entiy.User;
import com.example.danmudemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserMapper userMapper;

    @PostMapping("/login")
    public AjaxResult login(@RequestBody User loginUser) {
        User user = userMapper.queryUserByUsername(loginUser.getUsername());
        if (user != null && user.getPassword().equals(loginUser.getPassword())) {
            StpUtil.login(user.getId());
            return AjaxResult.success("Login success");
        }
        return AjaxResult.fail("Account or password error");
    }
    @RequestMapping("/isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

//    @PostMapping("/register")
//    public AjaxResult register(String username, String password) {
//        if (!StringUtils.isEmpty(username) || !StringUtils.isEmpty(password)) {
//            return AjaxResult.fail();
//        }
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        int count = userMapper.insertUser(user);
//        if (count > 0) {
//            return AjaxResult.success("Register success");
//        }
//        return AjaxResult.fail("Register failed");
//    }
}

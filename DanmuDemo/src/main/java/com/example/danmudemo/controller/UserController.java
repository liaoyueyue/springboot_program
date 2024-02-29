package com.example.danmudemo.controller;

import com.example.danmudemo.entiy.User;
import com.example.danmudemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/all")
    public List<User> questUserList() {
        List<User> users = userService.getAllUser();
        return users;
    }
}

package org.example.orderdemo.controller;

import org.example.orderdemo.common.AjaxResult;
import org.example.orderdemo.entity.User;
import org.example.orderdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-02-29
 * Time: 19:36
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/all")
    public AjaxResult showUserList() {
        return AjaxResult.success(userService.queryAllUser());
    }

}

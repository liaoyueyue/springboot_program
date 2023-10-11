package com.example.myblogssm.controller;

import com.example.myblogssm.common.AjaxResult;
import com.example.myblogssm.entity.UserInfo;
import com.example.myblogssm.service.UserInfoService;
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
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

    @PostMapping("/register")
    public Integer addUserInfo(UserInfo userInfo) {
        return userInfoService.addUserInfo(userInfo);
    }

    @PostMapping("/login")
    public AjaxResult login(UserInfo userInfo) {
        UserInfo localUserInfo = userInfoService.selectUserInfo(userInfo);
        if (localUserInfo == null) {
            return AjaxResult.fail(404, "No userinfo found");
        }
        return AjaxResult.success(localUserInfo);
    }

}

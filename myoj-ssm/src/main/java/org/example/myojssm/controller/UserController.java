package org.example.myojssm.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.myojssm.common.AjaxResult;
import org.example.myojssm.common.utils.UniqueUsernameUtil;
import org.example.myojssm.common.utils.UserSessionUtil;
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
    public AjaxResult login(HttpServletRequest request, String username, String password, String captcha) {
        // 1.非空校验
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(password) || !StringUtils.hasLength(captcha)) {
            return AjaxResult.fail();
        }
        // 2.判断验证码 --这里暂时做简单判断
        if (captcha.equals(" ")) {
            return AjaxResult.fail(-1, "captcha error");
        }
        // 3.判断用户有效和密码是否正确
        User user = userService.queryOneByUsername(username);
        if (user != null && user.getId() > 0) {
            // 有效用户判断密码
            if (user.getPassword().equals(password)) {
                user.setPassword("");
                UserSessionUtil.setSessionUser(request, user);
                return AjaxResult.success(user);
            }
        }
        return AjaxResult.fail(-1, "illegal username or password");
    }

    @PostMapping("/register")
    public AjaxResult register(User user, String vercode) {
        // 1.非空验证
        if (!StringUtils.hasLength(user.getEmail()) || !StringUtils.hasLength(vercode) || !StringUtils.hasLength(user.getPassword()) || !StringUtils.hasLength(user.getNickname())) {
            return AjaxResult.fail();
        }
        // 2.判断邮箱验证码 --这里暂时做简单判断
        if (vercode.equals(" ")) {
            return AjaxResult.fail(-1, "vercode error");
        }
        // 3.尝试创建用户
        String uniqueUsername = UniqueUsernameUtil.getUsername(user.getNickname(), userService);
        user.setUsername(uniqueUsername);
        boolean isAddUser = userService.addUser(user);
        return AjaxResult.success(isAddUser);
    }
}

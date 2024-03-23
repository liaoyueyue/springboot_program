package org.example.myojssm.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.example.myojssm.common.Result;
import org.example.myojssm.common.utils.UniqueUsernameUtil;
import org.example.myojssm.common.utils.UserSessionUtil;
import org.example.myojssm.entity.User;
import org.example.myojssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
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
@Validated
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Result login(HttpServletRequest request, String account, String password, @NotBlank @Pattern(regexp = "^\\S{4,6}$") String captcha) {
        // 1.判断验证码 --这里暂时做简单判断
        if (captcha.equals(" ")) {
            return Result.fail("captcha error");
        }
        // 2.执行用户登录
        User user = userService.login(request, account, password);
        if (user != null) {
            return Result.success(user);
        }
        return Result.fail("illegal account or password");
    }

    @PostMapping("/register")
    public Result register(@NotBlank @Email String email, @NotBlank @Pattern(regexp = "^\\S{2,16}$") String nickname, @NotBlank @Pattern(regexp = "^\\S{6,16}$") String password, String vercode) {
        // 1.判断邮箱是否存在
        boolean isEmailExist = userService.queryEmailExist(email);
        if (isEmailExist) {
            return Result.fail("Email exist");
        }
        // 2.判断邮箱验证码 --这里暂时做简单判断
        if (vercode.equals(" ")) {
            return Result.fail("Vercode error");
        }
        // 3.尝试创建用户
        String uniqueUsername = UniqueUsernameUtil.getUsername(nickname);
        User user = new User(null, uniqueUsername, password, nickname, email, null, null, null);
        boolean isAddUser = userService.addUser(user);
        return Result.success(isAddUser ? uniqueUsername : "Username exist");
    }

    public Result getUserinfoList() {
        return null;
    }
}

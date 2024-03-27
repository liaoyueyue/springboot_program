package org.example.myojssm.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.example.myojssm.common.Result;
import org.example.myojssm.common.utils.ThreadLocalUtil;
import org.example.myojssm.entity.User;
import org.example.myojssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public Result login(@NotBlank @Pattern(regexp = "^\\S{2,25}$") String account, @NotBlank @Pattern(regexp = "^\\S{6,16}$") String password, @NotBlank @Pattern(regexp = "^\\S{4,6}$") String captcha) {
        // 1.判断图像验证码 --这里暂时做简单判断
        if (captcha.equals(" ")) {
            return Result.fail("captcha error");
        }
        // 2.执行用户登录
        String token = userService.login(account, password);
        return token != null ? Result.success(token) : Result.fail("illegal account or password");
    }

    @PostMapping("/register")
    public Result register(@NotBlank @Email String email, @NotBlank @Pattern(regexp = "^\\S{2,16}$") String nickname, @NotBlank @Pattern(regexp = "^\\S{6,16}$") String password, @NotBlank @Pattern(regexp = "^\\S{4,6}$") String vercode) {
        // 1.判断邮箱是否存在
        boolean isEmailExist = userService.queryEmailExist(email);
        if (isEmailExist) {
            return Result.fail("Email exist");
        }
        // 2.判断邮箱验证码 --这里暂时做简单判断
        if (vercode.equals(" ")) {
            return Result.fail("Vercode error");
        }
        // 3.尝试创建用户， 如果创建成功则返回用户名
        String username = userService.register(email, password, nickname);
        return username != null ? Result.success(username) : Result.fail("Username exist");
    }

    @GetMapping("/userinfo")
    public Result getUserInfo() {
        User user = userService.getUserInfo();
        return Result.success(user);
    }

    @PostMapping("/updateinfo")
    public Result updateUserInfo(@RequestBody @Validated User user) {
        return userService.updateUserInfo(user) > 0 ? Result.success() : Result.fail("Update failed, Check if it is the current user");
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(String avatarUrl) {
        return userService.updateAvatar(avatarUrl) > 0 ? Result.success() : Result.fail("Update failed");
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params) {
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        // 1.参数校验
        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd)) {
            return Result.fail();
        }
        // 2.更新密码
        return userService.updatePassword(oldPwd, newPwd) > 0 ? Result.success() : Result.fail("Update failed, Check old password");
    }
}

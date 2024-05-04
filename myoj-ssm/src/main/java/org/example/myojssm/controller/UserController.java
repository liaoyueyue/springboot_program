package org.example.myojssm.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.example.myojssm.common.Result;
import org.example.myojssm.entity.User;
import org.example.myojssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

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
    private UserService userService;

    @PostMapping("/login")
    public Result login(@NotBlank @Pattern(regexp = "^\\S{2,25}$") String account, @NotBlank @Pattern(regexp = "^\\S{6,16}$") String password, @NotBlank @Pattern(regexp = "^\\S{4,6}$") String captcha) {
        // 1.判断图像验证码 --这里暂时做简单判断
        if (captcha.equals(" ")) {
            return Result.fail("captcha error");
        }
        // 2.执行用户登录
        return userService.login(account, password);
    }

    @PostMapping("/register")
    public Result register(@NotBlank @Email String email, @NotBlank @Pattern(regexp = "^\\S{4,6}$") String vercode, @NotBlank @Pattern(regexp = "^\\S{6,16}$") String password, @NotBlank @Pattern(regexp = "^\\S{2,16}$") String nickname) {
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
        return userService.register(email, password, nickname);
    }

    @GetMapping("/userinfo")
    public Result getUserInfo() {
        return Result.success(userService.getUserInfo());
    }

    @PutMapping("/updateinfo")
    public Result updateUserInfo(@RequestBody @Validated(User.Update.class) User user) {
        return userService.updateUserInfo(user);
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@NotNull MultipartFile avatarFile) {
        return userService.updateAvatar(avatarFile);
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@NotBlank @Pattern(regexp = "^\\S{6,16}$") @RequestParam("old_pwd") String oldPwd, @NotBlank @Pattern(regexp = "^\\S{6,16}$") @RequestParam("new_pwd") String newPwd, @RequestHeader("Authorization") String oldToken) {
        return userService.updatePwd(oldPwd, newPwd, oldToken);
    }
}

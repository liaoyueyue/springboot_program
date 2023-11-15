package com.example.myblogssm.controller;

import com.example.myblogssm.common.AjaxResult;
import com.example.myblogssm.common.utils.EmailUtils;
import com.example.myblogssm.common.utils.UserSessionUtils;
import com.example.myblogssm.entity.User;
import com.example.myblogssm.service.EmailService;
import com.example.myblogssm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-11-06
 * Time: 23:47
 */
@Controller
@ResponseBody
@Slf4j
@RequestMapping("/email")
public class EmailController {
    @Autowired
    EmailService emailService;
    @Autowired
    UserService userService;
    @PostMapping("/sendverificationcodeforregister")
    public AjaxResult sendVerificationCodeForRegister(String email) {
        // 1.非空验证
        if (!StringUtils.hasLength(email)) {
            return AjaxResult.fail(-1, "illegal request");
        }
        // 2.邮箱校验
        // 邮箱格式判断
        if (!EmailUtils.isEmailValid(email)) {
            return AjaxResult.fail(-1, "Email is illegal");
        }
        // 邮箱是否存在
        int emailExist = userService.queryEmailExist(email);
        if (emailExist > 0) {
            return AjaxResult.fail(-1, "Email already exists");
        }
        // 3.发送6位验证码到用户邮箱
        try {
            String verificationCode = emailService.generateAndStoreVerificationCode(email);
            emailService.sendVerificationCode(email, verificationCode);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("发送验证码邮件时发生异常了！", e);
            return AjaxResult.fail(-1, "Email sending failed");
        }
        return AjaxResult.success(200, "Successfully sent");
    }

    @PostMapping("/sendverificationcodeforverification")
    public AjaxResult sendVerificationCodeForVerification(HttpServletRequest request) {
        // 1.得到当前登录用户
        User user = UserSessionUtils.getSessionUser(request);
        if (user == null) {
            return AjaxResult.fail(-1, "User not logged in");
        }
        String email = user.getEmail();
        // 2.发送6位验证码到用户邮箱
        try {
            String verificationCode = emailService.generateAndStoreVerificationCode(email);
            emailService.sendVerificationCode(email, verificationCode);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("发送验证码邮件时发生异常了！", e);
            return AjaxResult.fail(-1, "Email sending failed");
        }
        return AjaxResult.success(200, "Successfully sent");
    }
}

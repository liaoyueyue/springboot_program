package com.example.myblogssm.controller;

import com.example.myblogssm.common.utils.VerificationCodeUtils;
import com.example.myblogssm.service.EmailService;
import org.springframework.stereotype.Controller;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-11-06
 * Time: 0:45
 */
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/sendverificationcode")
    public void sendVerificationCode(String email) {
        String code = VerificationCodeUtils.generateCode(6); // 生成6位验证码
        emailService.sendVerificationCode(email, code);
    }
}


package com.example.myblogssm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-11-06
 * Time: 0:21
 */
@Service
public class EmailService {
    @Autowired
    JavaMailSender mailSender;

    public void sendVerificationCode(String toEmail, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("liaoyueyue.email@qq.com");
        message.setTo(toEmail);
        message.setSubject("个人博客系统");
        message.setText("您的验证码为: " + code);
        mailSender.send(message);
    }
}

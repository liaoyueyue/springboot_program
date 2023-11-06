package com.example.myblogssm.service;

import com.example.myblogssm.common.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

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

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 发送验证码到邮箱
     * @param toEmail 用户的邮箱
     * @param code 验证码
     */
    public void sendVerificationCode(String toEmail, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("liaoyueyue.email@qq.com");
        message.setTo(toEmail);
        message.setSubject("个人博客系统");
        message.setText("您的验证码为: " + code);
        mailSender.send(message);
    }

    /**
     * 生成和存储验证码到 redis
     * @param email 邮箱
     * @return 邮箱验证码
     */
    public String generateAndStoreVerificationCode(String email) {
        String key = "verification_code:" + email;
        String code = EmailUtils.generateVerificationCode(6);
        redisTemplate.opsForValue().set(key, code, 5, TimeUnit.MINUTES); // 设置验证码的过期时间为5分钟
        return code;
    }

    /**
     * 从 redis 中拿 验证码
     * @param email 邮箱
     * @return 邮箱验证码
     */
    public String getVerificationCode(String email) {
        String key = "verification_code:" + email;
        return (String) redisTemplate.opsForValue().get(key);
    }
}

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
        message.setFrom("a973044869@163.com");
        message.setTo(toEmail);
        message.setSubject("个人博客系统");
        message.setText("您的验证码为: " + code + "。 如果不是您操作的请不要将验证码给他人。");
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

    /**
     * 检查验证码是否还有效
     * @param email 邮箱
     * @param code 验证码
     * @return
     */
    public boolean isVerificationCodeValid(String email, String code) {
        // 拿 redis 中的验证码
        String storedCode = getVerificationCode(email);
        return code != null && code.equals(storedCode);
    }

    /**
     * 删除Redis中的验证码，用户登录后可以先检验是否有效后进行删除
     * @param email 邮箱
     */
    public void deleteVerificationCode(String email) {
        String key = "verification_code:" + email;
        redisTemplate.delete(key);
    }

    /**
     * 存储通过安全验证的邮箱到 redis
     * @param email 邮箱
     */
    public void storeSecurityVerification(String email) {
        String key = "security_verification:" + email;
        String value = "true";
        redisTemplate.opsForValue().set(key, value, 5, TimeUnit.MINUTES); // 设置验证码的过期时间为5分钟
    }

    /**
     * 判断是否为通过安全验证的邮箱
     * @param email 邮箱
     */
    public boolean isSecurityVerifiedEmail(String email) {
        String key = "security_verification:" + email;
        String value = (String) redisTemplate.opsForValue().get(key);
        return value != null && value.equals("true");
    }

    /**
     * 从 redis 删除通过安全验证的邮箱
     * @param email 邮箱
     */
    public void deleteSecurityVerification(String email) {
        String key = "security_verification:" + email;
        redisTemplate.delete(key);
    }
}

package com.example.myblogssm.common;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * Description: 加盐工具类
 * User: liaoyueyue
 * Date: 2023-10-20
 * Time: 19:39
 */
public class PasswordUtils {
    /**
     * 加密 - 根据随机盐值给密码加盐并生成最终密码
     * @param password 需要加密的密码 - 用户的第一次密码或者需要修改的密码
     * @return 加密的密码
     */
    public static String encrypt(String password) {
        // 盐(32位)； salt 盐
        String salt = UUID.randomUUID().toString().replace("-", "");
        // digest 摘要；文摘；汇编；概要
        String saltPassword = addSalt(password, salt);
        // 最终密码 65位
        String finalPassword = salt + "$" + saltPassword;
        return finalPassword;
    }

    /**
     * 加密 - 根据指定盐值给密码加盐并生成最终密码
     * @param password 需要加密的密码
     * @param salt 盐值
     * @return 加密的密码
     */
    public static String encrypt(String password, String salt) {
        String saltPassword = addSalt(password, salt);
        String finalPassword = salt + "$" + saltPassword;
        return finalPassword;
    }

    /**
     * 解密 - 验证密码
     * @param inputPassword 用户输入密码
     * @param finalPassword 数据库中密码
     * @return 密码是否正确
     */
    public static boolean decrypt(String inputPassword, String finalPassword) {
        if (StringUtils.hasLength(inputPassword) && StringUtils.hasLength(finalPassword) && finalPassword.length() == 65) {
            // 在数据库中取出最终密码中的盐值
            String salt = finalPassword.substring(0, 32);
            // 根据指定盐值获取最终密码和数据库中密码进行对比
            return encrypt(inputPassword, salt).equals(finalPassword);
        }
        return false;
    }

    /**
     * 加盐操作
     * @param password 被加盐密码
     * @param salt 盐值
     * @return 加盐的密码
     */
    private static String addSalt(String password, String salt) {
        // addSaltStd 加盐标准字符串
        String addSaltsStd = salt + password;
        // md5DigestAsHex() 进行md5加密
        return DigestUtils.md5DigestAsHex((addSaltsStd).getBytes(StandardCharsets.UTF_8));
    }
}

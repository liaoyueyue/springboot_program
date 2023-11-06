package com.example.myblogssm.common.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * Description: 邮箱工具类
 * User: liaoyueyue
 * Date: 2023-11-06
 * Time: 1:05
 */
public class EmailUtils {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    /**
     * 判断邮箱格式是否合法
     * @param email 用户邮箱
     * @return 是否为合法邮箱
     */
    public static boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * 根据 length 来生成随机验证码
     * 使用Apache Commons Lang库中的`RandomStringUtils`来生成随机验证码。创建一个工具类来生成验证码
     * @param length 验证码长度
     * @return 指定长度验证码
     */
    public static String generateVerificationCode(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }
}

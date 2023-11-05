package com.example.myblogssm.common.utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-11-06
 * Time: 0:46
 */
public class VerificationCodeUtils {
    public static String generateCode(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }
}

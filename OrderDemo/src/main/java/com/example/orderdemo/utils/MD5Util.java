package com.example.orderdemo.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String getMD5(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            md.update(value.getBytes());
            value = new BigInteger(1,md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return value;
    }
}

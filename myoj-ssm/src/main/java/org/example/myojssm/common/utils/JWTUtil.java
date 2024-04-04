package org.example.myojssm.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: JWT 工具类
 * User: liaoyueyue
 * Date: 2024-03-24
 * Time: 12:14
 */

public class JWTUtil {
    private static final String KEY = "MY_JWT_SECRET_KEY";
    private static final int EXPIRES = 60; //分钟

    // claims 主张、要求、获取
    public static String genToken(Map<String, Object> claims) {
        return JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 60000L * EXPIRES))
                .sign(Algorithm.HMAC256(KEY));
    }

    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }
}



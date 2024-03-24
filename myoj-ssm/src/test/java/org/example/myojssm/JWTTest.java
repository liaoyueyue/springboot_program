package org.example.myojssm;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-03-24
 * Time: 12:21
 */
@SpringBootTest
public class JWTTest {
    private final String secret = "testJWT";

    @Test
    public void testGen() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "admin");
        String token = JWT.create()
                .withClaim("user", claims) // 添加载荷
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12)) // 添加过期时间
                .sign(Algorithm.HMAC256(secret)); // 指定算法配置密钥
        System.out.println("token = " + token);
    }

    @Test
    public void testParse() {
        // 定义字符串， 模拟用户传递过来的token
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6ImFkbWluIn0sImV4cCI6MTcxMTI5NzcyOX0.RjpCcICehSQ8J8gHEIwqjDptwymp6qijHJJ0vfeK1RY";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build(); // JWTVerifier JWT验证者
        DecodedJWT decodedJWT = jwtVerifier.verify(token); // 验证token， 生成一个解析后的JWT对象
        Map<String, Claim> claims = decodedJWT.getClaims();
        System.out.println("claims.get(\"user\") = " + claims.get("user"));
    }

}

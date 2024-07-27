package org.example.myojssm.config.interceptors;

import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.myojssm.common.exception.TokenNotFound;
import org.example.myojssm.common.utils.JWTUtil;
import org.example.myojssm.common.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * Description: 登录拦截器
 * User: liaoyueyue
 * Date: 2024-03-24
 * Time: 14:16
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Value("${jwt.header-key}")
    private String TOKEN_HEADER_KEY;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String token = request.getHeader(TOKEN_HEADER_KEY);
            if (token == null) {
                throw new TokenNotFound("token is null");
            }
            Map<String, Object> claims = JWTUtil.parseToken(token);
            String localUsername = (String) claims.get("username");
            // 从 redis 获取 登录用户对应的 token
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            String redisToken = operations.get(localUsername);
            if (redisToken == null || !redisToken.equals(token)) {
                // token 失效
                throw new RuntimeException("Token failure");
            }
            ThreadLocalUtil.set(claims);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.remove();
    }
}

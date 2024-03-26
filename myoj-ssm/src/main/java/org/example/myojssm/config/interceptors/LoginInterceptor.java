package org.example.myojssm.config.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.myojssm.common.utils.JWTUtil;
import org.example.myojssm.common.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Value;
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
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String token = request.getHeader(TOKEN_HEADER_KEY);
            Map<String, Object> claims = JWTUtil.parseToken(token);
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

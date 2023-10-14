package com.example.myblogssm.config;

import com.example.myblogssm.common.AppConstant;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-10-14
 * Time: 21:42
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute(AppConstant.USER_SESSION_KEY) != null) {
            // 用户已经登录
            return true;
        }
        // 用户未登录
        response.sendRedirect("/login.html");
        return false;
    }
}

package org.example.myojssm.common.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.myojssm.common.AppConstant;
import org.example.myojssm.entity.User;

/**
 * Created with IntelliJ IDEA.
 * Description: 用户会话工具类
 * User: liaoyueyue
 * Date: 2024-03-15
 * Time: 21:05
 */
public class UserSessionUtil {
    private static final String USER_SESSION_KEY = AppConstant.USER_SESSION_KEY;
    /**
     * 设置用户会话
     * @param request 客户端请求
     * @param user 用户实体
     */
    public static void setSessionUser(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute(USER_SESSION_KEY, user);
    }

    /**
     * 获取用户会话
     * @param request 客户端请求
     * @return 客户端请求用户实体
     */
    public static User getSessionUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute(USER_SESSION_KEY) != null) {
            return (User) session.getAttribute(USER_SESSION_KEY);
        }
        return null;
    }

    /**
     * 更新用户会话
     * @param request 客户端请求
     * @param user 客户端请求用户实体
     */
    public static void updateSessionUser(HttpServletRequest request, User user) {
        HttpSession session = request.getSession(false);
        session.setAttribute(USER_SESSION_KEY, user);
    }
}

package com.example.myblogssm.common.utils;

import com.example.myblogssm.common.AppConstant;
import com.example.myblogssm.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-10-15
 * Time: 1:13
 */
public class UserSessionUtils {
    /**
     * 得到当前登录用户
     * @return 用户实体
     */
    public static User getSessionUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute(AppConstant.USER_SESSION_KEY) != null) {
            return (User) session.getAttribute(AppConstant.USER_SESSION_KEY);
        }
        return null;
    }

    public static void setSessionUser(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute(AppConstant.USER_SESSION_KEY, user);
    }

    public static void updateSession(HttpServletRequest request, User user) {
        HttpSession session = request.getSession(false);
        session.setAttribute(AppConstant.USER_SESSION_KEY, user);
    }

}

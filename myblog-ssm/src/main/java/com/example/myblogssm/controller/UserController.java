package com.example.myblogssm.controller;

import com.example.myblogssm.common.AjaxResult;
import com.example.myblogssm.common.AppConstant;
import com.example.myblogssm.common.UserSessionUtils;
import com.example.myblogssm.entity.User;
import com.example.myblogssm.entity.vo.UserVo;
import com.example.myblogssm.service.ArticleService;
import com.example.myblogssm.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-10-11
 * Time: 17:58
 */
@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @PostMapping("/register")
    public AjaxResult register(User user) {
        // 非空校验
        if (user == null || !StringUtils.hasLength(user.getUsername()) || !StringUtils.hasLength(user.getPassword())) {
            return AjaxResult.fail(-1, "username or password not found");
        }
        return AjaxResult.success(userService.addUser(user));
    }

    @PostMapping("/login")
    public AjaxResult login(HttpServletRequest request, String username, String password) {
        // 非空校验
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(password)) {
            return AjaxResult.fail(-1, "illegal request");
        }
        User user = userService.queryUserByName(username);
        if (user != null && user.getId() > 0) {
            // 有效的用户, 判断密码
            if (password.equals(user.getPassword())) {
                user.setPassword("");
                HttpSession session = request.getSession();
                session.setAttribute(AppConstant.USER_SESSION_KEY, user);
                return AjaxResult.success(user);
            }
        }
        // 用户为空或者密码错误
        return AjaxResult.fail(-1, "username or password not found");
    }

    @PostMapping("/showinfo")
    public AjaxResult showInfo(HttpServletRequest request) {
        UserVo userVo = new UserVo();
        // 1.得到当前登录用户
        User user = UserSessionUtils.getSessionUser(request);
        if (user == null) {
            return AjaxResult.fail(-1, "illegal request");
        }
        BeanUtils.copyProperties(user, userVo); // Spring 提供的深克隆方法
        // 2.得到用户发表文章总数
        userVo.setArticleTotal(articleService.queryArticleTotalByUid(user.getId()));
        return AjaxResult.success(userVo);
    }

}

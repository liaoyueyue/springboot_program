package com.example.myblogssm.controller;

import com.example.myblogssm.common.AjaxResult;
import com.example.myblogssm.common.AppConstant;
import com.example.myblogssm.common.PasswordUtils;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

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
        // 给密码加密
        user.setPassword(PasswordUtils.encrypt(user.getPassword()));
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
            if (PasswordUtils.decrypt(password, user.getPassword())) {
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

    @PostMapping("/showinfobyid")
    public AjaxResult showInfoById(Integer id) {
        if (id == null || id <= 0) {
            return AjaxResult.fail(-1, "illegal request");
        }
        User user = userService.queryUserById(id);
        if (user != null) {
            user.setPassword("");
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user, userVo);
            userVo.setArticleTotal(articleService.queryArticleTotalByUid(id));
            return AjaxResult.success(userVo);
        }
        return AjaxResult.fail(-1, "user not found");
    }

    @PostMapping("/logout")
    public AjaxResult logout(HttpSession session) {
        session.removeAttribute(AppConstant.USER_SESSION_KEY);
        return AjaxResult.success(1);
    }

    @PostMapping("/updateinfo")
    public AjaxResult updateInfo(@RequestPart("photo") MultipartFile photo) throws IOException {
        //获取文件名
        String fileName = photo.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //重新生成文件名
        fileName = UUID.randomUUID().toString().replace("-", "")+suffixName;
        //指定本地文件夹存储图片，写到需要保存的目录下
        String filePath = "C:\\users\\liaoyueyue\\desktop\\";
        //将图片保存到static文件夹里
        photo.transferTo(new File(filePath+fileName));
        return AjaxResult.success(200);
    }

}

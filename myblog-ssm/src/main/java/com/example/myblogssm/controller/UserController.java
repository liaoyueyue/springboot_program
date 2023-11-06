package com.example.myblogssm.controller;

import com.example.myblogssm.common.*;
import com.example.myblogssm.common.utils.*;
import com.example.myblogssm.entity.User;
import com.example.myblogssm.entity.vo.UserVo;
import com.example.myblogssm.service.ArticleService;
import com.example.myblogssm.service.EmailService;
import com.example.myblogssm.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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

    @Autowired
    EmailService emailService;

    @PostMapping("/register")
    public AjaxResult register(User user, String verificationCode) {
        // 1.非空校验
        if (user == null || !StringUtils.hasLength(user.getNickname()) || !StringUtils.hasLength(user.getPassword()) || !StringUtils.hasLength(user.getEmail()) || !StringUtils.hasLength(verificationCode)) {
            return AjaxResult.fail(-1, "illegal request");
        }
        // 2. 邮箱验证码校验
        if (!verificationCode.equals(emailService.getVerificationCode(user.getEmail()))) {
            return AjaxResult.fail(-1, "Verification code error");
        }
        // 3.生成用户
        // 生成唯一用户名
        String uniqueUsername = UniqueUsernameUtils.getUsername(user.getNickname(), userService);
        user.setUsername(uniqueUsername);
        // 给密码加密
        user.setPassword(PasswordUtils.encrypt(user.getPassword()));
        int count = userService.addUser(user);
        HashMap<String, Object> result = new HashMap<>();
        result.put("count", count);
        result.put("username", uniqueUsername);
        return AjaxResult.success(result);
    }

    @PostMapping("/login")
    public AjaxResult login(HttpServletRequest request, String username, String password) {
        System.out.println(request.getHeader("User-Agent"));
        // 非空校验
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(password)) {
            return AjaxResult.fail(-1, "illegal request");
        }
        User user = userService.queryUserByName(username);
        if (user != null && user.getId() > 0) {
            // 有效的用户, 判断密码
            if (PasswordUtils.decrypt(password, user.getPassword())) {
                user.setPassword("");
                UserSessionUtils.setSessionUser(request, user);
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

    @PostMapping("/updatephoto")
    public AjaxResult updatePhoto(HttpServletRequest request, @RequestPart("photo") MultipartFile photo) throws IOException {
        // 1.非空校验
        if (photo.isEmpty()) {
            return AjaxResult.fail(403, "Image error");
        }
        // 2.获取需要更新头像的用户信息
        User user = UserSessionUtils.getSessionUser(request);
        if (user == null) {
            return AjaxResult.fail(-1, "illegal request");
        }
        // 3.用户不是默认头像需要修改头像
        if (!user.getPhoto().equals("images/defaultPhoto.jpg")) {
            // 删除旧头像
            File file = new File(AppConstant.IMG_PATH_ABSOLUTE + user.getPhoto());
            file.delete();
        }
        // 3.处理文件名和路径
        // 获取用户上传的文件名
        String fileName = photo.getOriginalFilename();
        // 获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 重新生成文件名
        fileName = UUID.randomUUID().toString().replace("-", "") + suffixName;
        // 图片保存路径-绝对路径用来保存文件，相对路径提供给浏览器访问
        String photoPathAbsolute = AppConstant.IMG_PATH_ABSOLUTE + fileName; //绝对路径
        String photoPathRelative = AppConstant.IMG_PATH_RELATIVE + fileName; //相对路径
        try {
            // 将上传文件绝对路径保存到服务器文件夹
            photo.transferTo(new File(photoPathAbsolute));
            // 保存图片相对路径到数据库中
            userService.updatePhotoById(user.getId(), photoPathRelative);
            // 更新用户会话信息
            user.setPhoto(photoPathRelative);
            UserSessionUtils.updateSession(request, user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 将图片相对路径返回给前端
        return AjaxResult.success(photoPathRelative);
    }

    @PostMapping("/updateinfo")
    public AjaxResult updateInfo(HttpServletRequest request, User newUser) {
        // 1.非空校验
        if (newUser == null || !StringUtils.hasLength(newUser.getNickname()) || !StringUtils.hasLength(newUser.getCodeCloud())) {
            return AjaxResult.fail(-1, "illegal parameter");
        }
        // 2.获取需要更新的用户信息
        User user = UserSessionUtils.getSessionUser(request);
        if (user == null) {
            return AjaxResult.fail(-1, "illegal request");
        }
        // 3.更新用户信息
        user.setNickname(newUser.getNickname());
        user.setCodeCloud(newUser.getCodeCloud());
        int result = userService.updateUser(user);
        // 4.更新会话信息
        UserSessionUtils.updateSession(request, user);
        return AjaxResult.success(200, result);
    }

    @PostMapping("/updatepassword")
    public AjaxResult updatePassword(HttpServletRequest request, String password, String newPassword) {
        // 1.非空校验
        if (!StringUtils.hasLength(password) || !StringUtils.hasLength(newPassword)) {
            return AjaxResult.fail(-1, "illegal parameter");
        }
        // 2.获取会话中用户信息
        User user = UserSessionUtils.getSessionUser(request);
        if (user == null) {
            return AjaxResult.fail(-1, "illegal request");
        }
        // 3.从数据库中获取用户信息并判断密码是否正确
        user = userService.queryUserById(user.getId());
        if (PasswordUtils.decrypt(password, user.getPassword())) {
            // 4. 返回数据
            int result = userService.updatePassword(user.getId(), PasswordUtils.encrypt(newPassword));
            return AjaxResult.success(200, result);
        }
        return AjaxResult.fail(-1, "illegal request");
    }
}

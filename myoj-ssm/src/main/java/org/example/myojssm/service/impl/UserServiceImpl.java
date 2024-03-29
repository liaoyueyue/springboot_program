package org.example.myojssm.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.example.myojssm.common.Result;
import org.example.myojssm.common.utils.JWTUtil;
import org.example.myojssm.common.utils.ThreadLocalUtil;
import org.example.myojssm.common.utils.UniqueUsernameUtil;
import org.example.myojssm.entity.User;
import org.example.myojssm.mapper.UserMapper;
import org.example.myojssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-03-10
 * Time: 17:02
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public String login(String account, String password) {
        // 判断用户有效和密码是否正确
        User user = userMapper.queryUserByEmailOrUsername(account);
        if (user != null) {
            // 有效用户判断密码
            if (user.getPassword().equals(password)) {
                // 有效密码，登录成功，返回JWT令牌
                Map<String, Object> claims = new HashMap<>();
                claims.put("id", user.getId());
                claims.put("username", user.getUsername());
                return JWTUtil.genToken(claims);
            }
        }
        return null;
    }

    @Override
    public String register(String email, String password, String nickname) {
        String uniqueUsername = UniqueUsernameUtil.getUsername(nickname);
        User user = new User(null, uniqueUsername, password, nickname, email, null, null, null);
        return userMapper.insertUser(user) > 0 ? uniqueUsername : null;
    }

    @Override
    public User queryUserByUsername(String username) {
        return userMapper.queryUserByUsername(username);
    }

    @Override
    public User queryUserById(int id) {
        return userMapper.queryUserById(id);
    }

    @Override
    public boolean queryUsernameExist(String username) {
        return userMapper.queryUsernameExist(username) > 0;
    }

    @Override
    public boolean queryEmailExist(String email) {
        return userMapper.queryEmailExist(email) > 0;
    }

    @Override
    public int updateUserInfo(User user) {
        // 判断是否为当前用户
        User loginUser = getUserInfo();
        if (!loginUser.getId().equals(user.getId())) {
            return 0;
        }
        return userMapper.updateUser(user);
    }

    @Override
    public int updateAvatar(String avatarUrl) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        return userMapper.updateAvatar(avatarUrl, id);
    }

    @Override
    public int updatePassword(String oldPwd, String newPwd) {
        User loginUser = getUserInfo();
        if (!loginUser.getPassword().equals(oldPwd)) { // 密码没有加密，这里简单判断
            return 0;
        }
        return userMapper.updatePwd(loginUser.getId(), newPwd);
    }

    @Override
    public User getUserInfo() {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        return userMapper.queryUserById(id);
    }

}

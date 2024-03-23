package org.example.myojssm.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.example.myojssm.common.utils.UserSessionUtil;
import org.example.myojssm.entity.User;
import org.example.myojssm.mapper.UserMapper;
import org.example.myojssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public User login(HttpServletRequest request, @NotBlank String account, @NotBlank @Pattern(regexp = "^\\S{6,16}$") String password) {
        User user = userMapper.queryUserByEmailOrUsername(account);
        if (user != null) {
            // 有效用户判断密码
            if (user.getPassword().equals(password)) {
                // 有效密码返回用户信息
                user.setPassword("");
                UserSessionUtil.setSessionUser(request, user);
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean addUser(User user) {
        return userMapper.addUser(user) > 0;
    }

    @Override
    public boolean queryUsernameExist(String username) {
        return userMapper.queryUsernameExist(username) > 0;
    }

    @Override
    public boolean queryEmailExist(String email) {
        return userMapper.queryEmailExist(email) > 0;
    }
}

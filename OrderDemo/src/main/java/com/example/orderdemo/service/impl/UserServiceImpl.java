package com.example.orderdemo.service.impl;

import com.example.orderdemo.common.Result;
import com.example.orderdemo.entity.User;
import com.example.orderdemo.mapper.UserMapper;
import com.example.orderdemo.service.UserService;
import com.example.orderdemo.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-03-28
 * Time: 9:02
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result login(String username, String password) {
        User user = userMapper.queryUserByUsername(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                return Result.success(user);
            }
        }
        return Result.fail("账号或者密码错误");
    }

    @Override
    public Result updateUserInfo(User user) {
        try {
            userMapper.updateUser(user);
            User user1 = userMapper.queryUserByUsername(user.getUsername());
            return Result.success("修改成功").data(user1);
        } catch (Exception e) {
            return Result.fail("修改失败");
        }
    }
}

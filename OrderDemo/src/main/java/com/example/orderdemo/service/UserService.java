package com.example.orderdemo.service;

import com.example.orderdemo.common.Result;
import com.example.orderdemo.entity.User;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-03-28
 * Time: 9:02
 */
public interface UserService {
    Result login(String username, String password);

    Result updateUserInfo(User user);
}

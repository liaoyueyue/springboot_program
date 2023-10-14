package com.example.myblogssm.service;

import com.example.myblogssm.entity.User;
import com.example.myblogssm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-10-11
 * Time: 17:57
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    public User queryUserByName(String username) {
        return userMapper.queryUserByName(username);
    }
}

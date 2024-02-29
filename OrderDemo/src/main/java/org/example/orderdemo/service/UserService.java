package org.example.orderdemo.service;

import org.example.orderdemo.entity.User;
import org.example.orderdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-02-29
 * Time: 19:36
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public List<User> queryAllUser() {
        return userMapper.queryAllUser();
    }

}

package org.example.myojssm.service.impl;

import org.example.myojssm.entity.User;
import org.example.myojssm.mapper.UserMapper;
import org.example.myojssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public User login(String email, String username) {
        User user;
        if (StringUtils.hasLength(email)) {
            user = userMapper.queryOneByEmail(email);
        } else {
            user = userMapper.queryOneByUsername(username);
        }
        return user;
    }

    @Override
    public boolean addUser(User user) {
        if (userMapper.addOne(user) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public int queryUsernameExist(String username) {
        return userMapper.queryUsernameExist(username);
    }
}

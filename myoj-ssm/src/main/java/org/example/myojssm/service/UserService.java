package org.example.myojssm.service;

import jakarta.annotation.Resource;
import org.example.myojssm.entity.User;
import org.example.myojssm.mapper.UserMapper;
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
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User queryOneByUsername(String username) {
        return userMapper.queryOneByUsername(username);
    }

}

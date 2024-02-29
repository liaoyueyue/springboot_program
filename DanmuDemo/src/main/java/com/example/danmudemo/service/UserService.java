package com.example.danmudemo.service;

import com.example.danmudemo.entiy.User;
import com.example.danmudemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }
}

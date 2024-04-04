package org.example.myojssm.service.impl;

import org.example.myojssm.common.Result;
import org.example.myojssm.common.utils.JWTUtil;
import org.example.myojssm.common.utils.ThreadLocalUtil;
import org.example.myojssm.common.utils.UniqueUsernameUtil;
import org.example.myojssm.entity.User;
import org.example.myojssm.mapper.UserMapper;
import org.example.myojssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-03-10
 * Time: 17:02
 */
@Service
public class UserServiceImpl implements UserService {
    @Value("${avatar-path}")
    private String AVATAR_PATH;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result login(String account, String password) {
        // 判断用户有效和密码是否正确
        User user = userMapper.queryUserByEmailOrUsername(account);
        if (user != null) {
            // 有效用户判断密码
            if (user.getPassword().equals(password)) {
                // 有效密码，登录成功，返回JWT令牌
                Map<String, Object> claims = new HashMap<>();
                claims.put("id", user.getId());
                claims.put("username", user.getUsername());
                String token = JWTUtil.genToken(claims);
                // 把 token 存储到 redis
                ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
                operations.set(user.getUsername(), token, 1, TimeUnit.HOURS);
                return Result.success(token);
            }
        }
        return Result.fail("illegal account or password");
    }

    @Override
    public Result register(String email, String password, String nickname) {
        String uniqueUsername = UniqueUsernameUtil.getUsername(nickname);
        User user = new User(null, uniqueUsername, password, nickname, email, null, null, null);
        return userMapper.insertUser(user) > 0 ? Result.success(uniqueUsername) : Result.fail("Username exist");
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
    public Result updateUserInfo(User user) {
        user.setId(getUserId());
        return userMapper.updateUser(user) > 0 ? Result.success() : Result.fail("Update failed");
    }

    @Override
    public Result updateAvatar(MultipartFile avatarFile) {
        String originalFilename = avatarFile.getOriginalFilename();
        String avatarName = UUID.randomUUID().toString().replace("-", "") + originalFilename.substring(originalFilename.lastIndexOf("."));
        try {
            avatarFile.transferTo(new File(AVATAR_PATH + avatarName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Integer id = getUserId();
        return userMapper.updateAvatar(avatarName, id) > 0 ? Result.success("/avatar/" + avatarName) : Result.fail("Update failed");
    }

    @Override
    public Result updatePwd(String oldPwd, String newPwd, String oldToken) {
        User loginUser = getUserInfo();
        if (oldPwd.equals(newPwd)) {
            return Result.fail("The old password is the same as the new password");
        }
        if (!loginUser.getPassword().equals(oldPwd)) { // 密码没有加密，这里简单判断
            return Result.fail("Update failed, Check old password");
        }
        if (userMapper.updatePwd(loginUser.getId(), newPwd) > 0) {
            stringRedisTemplate.delete(loginUser.getUsername()); // 删除旧 token
            return Result.success();
        }
        return Result.fail("Update failed");
    }

    @Override
    public User getUserInfo() {
        return userMapper.queryUserById(getUserId());
    }

    @Override
    public Integer getUserId() {
        Map<String, Object> claims = ThreadLocalUtil.get();
        return (Integer) claims.get("id");
    }
}

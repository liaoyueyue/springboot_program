package org.example.myojssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-04-04
 * Time: 12:43
 */
@RestController
public class RedisController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // 在 redis 存储数据
    @RequestMapping("/setrs")
    public String setRedis(String name, String value) {
        stringRedisTemplate.opsForValue().set(name, value,
                30, TimeUnit.SECONDS);
        return "Set redis success.";
    }

    // 读取 redis 中的数据
    @RequestMapping("/getrs")
    public String getRedis(String name) {
        Object valObj = stringRedisTemplate.opsForValue().get(name);
        if (valObj != null) return valObj.toString();
        return "Null";
    }
}

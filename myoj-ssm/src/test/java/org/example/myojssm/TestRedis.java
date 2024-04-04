package org.example.myojssm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-04-04
 * Time: 12:49
 */
@SpringBootTest //如果在测试类上添加了这个注解,那么将来单元测试方法执行之前,会先初始化Spring容器
public class TestRedis {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 在 redis 存储数据
     */
    @Test
    public void testSet() {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set("username", "zhangsan");
        operations.set("id", "1", 15, TimeUnit.SECONDS);
    }

    /**
     * 在 redis 取数据
     */
    @Test
    public void testGet() {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        System.out.println(operations.get("username"));
    }

}

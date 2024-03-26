package org.example.myojssm;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-03-26
 * Time: 19:21
 */
public class ThreadLocalTest {
    @Test
    public void testThreadLocalGetAndSet() {
        ThreadLocal<String> tl = new ThreadLocal<>();
        new Thread(() -> {
            tl.set("我是蓝色线程的线程局部变量");
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
        }, "蓝色").start();
        new Thread(() -> {
            tl.set("我是绿色线程的线程局部变量");
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
        }, "绿色").start();
    }
}

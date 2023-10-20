package com.example.myblogssm;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class MyBlogSSMApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void springSecurity() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pwd = "123";
        String finalPwd = passwordEncoder.encode(pwd);
        System.out.println("finalPwd = " + finalPwd);
        System.out.println("finalPwd2 = " + passwordEncoder.encode(pwd));
        System.out.println("finalPwd3 = " + passwordEncoder.encode(pwd));
        // 验证
        String inputPwd = "123";
        System.out.println(passwordEncoder.matches(inputPwd, finalPwd));
    }

}

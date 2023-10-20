package com.example.myblogssm.common;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-10-20
 * Time: 21:15
 */
@SpringBootTest
class PasswordUtilsTest {
    @Test
    void test1() {
        String userPwd = "123";
        String finalPwd = PasswordUtils.encrypt(userPwd);
        System.out.println("finalPwd = " + finalPwd);
        System.out.println(PasswordUtils.decrypt("123", finalPwd)? "验证成功":"验证失败");
    }

}
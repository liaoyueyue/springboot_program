package com.example.myblogssm.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-10-15
 * Time: 0:18
 */
@SpringBootTest
class ArticleMapperTest {
    @Autowired
    ArticleMapper articleMapper;

    @Test
    void queryArticleTotalByUid() {
        int result = articleMapper.queryArticleTotalByUid(2);
        System.out.println("result = " + result);
    }
}
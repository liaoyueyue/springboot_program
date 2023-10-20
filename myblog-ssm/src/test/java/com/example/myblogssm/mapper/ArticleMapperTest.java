package com.example.myblogssm.mapper;

import com.example.myblogssm.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

    @Test
    void qryUserArtListByUid() {
        int uid = 2;
        List<Article> articleList = articleMapper.qryUserArtListByUid(2);
        System.out.println(articleList != null);
        System.out.println(articleList);
        System.out.println(articleList.isEmpty());
    }

    @Test
    void queryArtListByPage() {
        List<Article> list = articleMapper.queryArtListByPage(1, 3);
        System.out.println(list);
    }
}
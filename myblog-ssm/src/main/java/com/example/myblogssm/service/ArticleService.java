package com.example.myblogssm.service;

import com.example.myblogssm.entity.Article;
import com.example.myblogssm.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-10-14
 * Time: 15:31
 */
@Service
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    public int queryArticleTotalByUid(Integer uid) {
        return articleMapper.queryArticleTotalByUid(uid);
    }

    public List<Article> qryUserArtListByUid(Integer uid) {
        return articleMapper.qryUserArtListByUid(uid);
    }

    public int delArticleById(Integer id, Integer uid) {
        return articleMapper.delArticleById(id, uid);
    }

    public Article queryArticleById(Integer id) {
        return articleMapper.queryArticleById(id);
    }

    public int updateRCount(Integer id) {
        return articleMapper.updateRCount(id);
    }

    public int addArticle(Article article) {
        return articleMapper.addArticle(article);
    }

    public int updateArticle(Article article) {
        return articleMapper.updateArticle(article);
    }

    public List<Article> queryArtListByPage(Integer pageSize, Integer startIndex) {
        return articleMapper.queryArtListByPage(pageSize, startIndex);
    }
}

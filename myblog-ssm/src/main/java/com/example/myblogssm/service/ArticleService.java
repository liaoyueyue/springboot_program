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

    public List<Article> qryUserArtListByUid(Integer uid, Integer pageSize, Integer startIndex, String sortOption) {
        List<Article> articleList = null;
        switch (sortOption) {
            case "mix":
                articleList = articleMapper.qryUserArtListByUid(uid, pageSize, startIndex);
                break;
            case "new":
                articleList = articleMapper.qryUserArtListByUidSortDate(uid, pageSize, startIndex);
                break;
            case "hot":
                articleList = articleMapper.qryUserArtListByUidSortRCount(uid, pageSize, startIndex);
                break;
        }
        return articleList;
    }

    public int delArticleById(Integer id, Integer uid) {
        return articleMapper.delArticleById(id, uid);
    }

    public Article queryArticleById(Integer id) {
        return articleMapper.queryArticleById(id);
    }

    public void updateRCount(Integer id) {
        articleMapper.updateRCount(id);
    }

    public int addArticle(Article article) {
        return articleMapper.addArticle(article);
    }

    public int updateArticle(Article article) {
        return articleMapper.updateArticle(article);
    }

    public List<Article> queryArtList(Integer pageSize, Integer startIndex, String sortOption) {
        List<Article> articleList = null;
        switch (sortOption) {
            case "mix":
                articleList = articleMapper.queryArtList(pageSize, startIndex);
                break;
            case "new":
                articleList = articleMapper.queryArtListSortDate(pageSize, startIndex);
                break;
            case "hot":
                articleList = articleMapper.queryArtListSortRCount(pageSize, startIndex);
                break;
        }
        return articleList;
    }
    public List<Article> queryArtListByTitle(String searchInfo, Integer pageSize, Integer startIndex, String sortOption) {
        List<Article> articleList = null;
        switch (sortOption) {
            case "mix":
                articleList = articleMapper.queryArtListByTitle(searchInfo, pageSize, startIndex);
                break;
            case "new":
                articleList = articleMapper.queryArtListByTitleSortDate(searchInfo, pageSize, startIndex);
                break;
            case "hot":
                articleList = articleMapper.queryArtListByTitleSortRCount(searchInfo, pageSize, startIndex);
                break;
        }
        return articleList;
    }

    public int queryArticleCount() {
        return articleMapper.queryArticleCount();
    }
    public int queryArticleCountByTitle(String searchInfo) {
        return articleMapper.queryArticleCountByTitle(searchInfo);
    }

    public int addDraft(Article article) {
        return articleMapper.addDraft(article);
    }

    public boolean queryIdExist(Integer id) {
        return articleMapper.queryIdExist(id)==1;
    }
}

package com.example.myblogssm.controller;

import com.example.myblogssm.common.AjaxResult;
import com.example.myblogssm.common.UserSessionUtils;
import com.example.myblogssm.entity.Article;
import com.example.myblogssm.entity.User;
import com.example.myblogssm.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-10-14
 * Time: 15:32
 */
@Controller
@ResponseBody
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @PostMapping("/showinfolist")
    public AjaxResult showInfoList(HttpServletRequest request) {
        User user = UserSessionUtils.getSessionUser(request);
        if (user == null) {
            return AjaxResult.fail(-1, "illegal request");
        }
        List<Article> articleList = articleService.qryUserArtListByUid(user.getId());
        if (!articleList.isEmpty()) {
            for (Article a : articleList) {
                String content = a.getContent();
                if (content.length() > 100) {
                    a.setContent(content.substring(0, 120) + "...");
                }
            }
        }
        return AjaxResult.success(articleList);
    }

    @PostMapping("/showinfo")
    public AjaxResult showInfo(Integer id) {
        if (id == null || id <= 0) {
            return AjaxResult.fail(-1, "illegal request");
        }
        Article article = articleService.queryArticleById(id);
        if (article == null) {
            return AjaxResult.fail(-1, "illegal request");
        }
        articleService.updateRCount(id);
        return AjaxResult.success(article);
    }

    @PostMapping("/delarticle")
    public AjaxResult delArticle(HttpServletRequest request, Integer id) {
        if (id == null || id <= 0) {
            return AjaxResult.fail(-1, "illegal request");
        }
        User user = UserSessionUtils.getSessionUser(request);
        if (user == null) {
            return AjaxResult.fail(-1, "illegal request");
        }
        return AjaxResult.success(articleService.delArticleById(id, user.getId()));
    }

    @PostMapping("/submitarticle")
    public AjaxResult submitArticle(HttpServletRequest request, Article article) {
        if (article == null || !StringUtils.hasLength(article.getTitle()) || !StringUtils.hasLength(article.getContent())) {
            return AjaxResult.fail(-1, "illegal parameter");
        }
        User user = UserSessionUtils.getSessionUser(request);
        if (user == null || user.getId() <= 0) {
            return AjaxResult.fail(-2, "invalid user");
        }
        article.setUid(user.getId());
        return AjaxResult.success(articleService.addArticle(article));
    }

    @PostMapping("/updatearticle")
    public AjaxResult updateArticle(HttpServletRequest request, Article article) {
        if (article == null || !StringUtils.hasLength(article.getTitle()) || !StringUtils.hasLength(article.getContent()) || article.getId() == null) {
            return AjaxResult.fail(-1, "illegal parameter");
        }
        User user = UserSessionUtils.getSessionUser(request);
        if (user == null || user.getId() == null) {
            return AjaxResult.fail(-2, "invalid user");
        }
        article.setUid(user.getId());
        return AjaxResult.success(articleService.updateArticle(article));
    }

}

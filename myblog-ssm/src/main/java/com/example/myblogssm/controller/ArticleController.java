package com.example.myblogssm.controller;

import com.example.myblogssm.common.AjaxResult;
import com.example.myblogssm.common.utils.UserSessionUtils;
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
import java.util.HashMap;
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

    @PostMapping("/showinfolistbypage")
    public AjaxResult showInfoListByPage(Integer pageIndex, Integer pageSize) {
        // 参数矫正
        if (pageIndex == null || pageIndex < 1) {
            pageIndex = 1;
        }
        if (pageSize == null || pageSize < 1 || pageSize > 5) {
            pageSize = 5;
        }
        // 查询中偏移量(offset)的值 = (当前页码-1)*每页显示条数
        int startIndex = (pageIndex - 1) * pageSize;
        List<Article> articleList = articleService.queryArtListByPage(pageSize, startIndex);
        if (articleList.isEmpty()) {
            return AjaxResult.fail(-1, "illegal request");
        }
        // 限制文章在文章列表页的字数
        for (Article a : articleList) {
            String content = a.getContent();
            if (content.length() > 100) {
                a.setContent(content.substring(0, 120) + "...");
            }
        }
        // 当前页面一共多少页
        // a) 查询文章总数
        int articleCount = articleService.queryArticleCount();
        // b) 页面总数 = 文章总数 / 页面显示文章数量 （同时进行进1）
        int pageCount = (int) Math.ceil(articleCount / (pageSize * 1.0));
        HashMap<String, Object> result = new HashMap<>();
        result.put("articleList", articleList);
        result.put("pageCount", pageCount);
        return AjaxResult.success(result);
    }

}

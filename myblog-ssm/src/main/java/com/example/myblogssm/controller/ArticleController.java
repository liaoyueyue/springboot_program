package com.example.myblogssm.controller;

import com.example.myblogssm.common.AjaxResult;
import com.example.myblogssm.common.utils.ArticleListUtils;
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
    @PostMapping("/showinfolistbyuid")
    public AjaxResult showInfoListByUid(HttpServletRequest request, Integer pageIndex, Integer pageSize, String sortOption) {
        // 参数矫正
        if (pageIndex == null || pageIndex < 1) {
            pageIndex = 1;
        }
        if (pageSize == null || pageSize < 1 || pageSize > 5) {
            pageSize = 5;
        }
        if (!(sortOption != null && (sortOption.equals("mix") || sortOption.equals("new") || sortOption.equals("hot")))) {
            sortOption = "mix";
        }
        // 查询中偏移量(offset)的值 = (当前页码-1)*每页显示条数
        int startIndex = (pageIndex - 1) * pageSize;
        // 从会话获取用户信息
        User user = UserSessionUtils.getSessionUser(request);
        if (user == null) {
            return AjaxResult.fail(-1, "illegal request");
        }
        List<Article> articleList = articleService.qryUserArtListByUid(user.getId(), pageSize, startIndex, sortOption);
        if (articleList.isEmpty()) {
            return AjaxResult.fail(-1, "Article not found");
        }
        // 限制单篇文章在文章列表页的字数并去除Markdown标记并提取纯文本
        ArticleListUtils.limitWordCountAndRemoveMarkdownTags(articleList, 120);
        // 当前页面一共多少页
        // a) 查询用户文章总数
        int articleCount = articleService.queryArticleTotalByUid(user.getId());
        // b) 页面总数 = 文章总数 / 页面显示文章数量 （同时进行进1）
        int pageCount = (int) Math.ceil(articleCount / (pageSize * 1.0));
        HashMap<String, Object> result = new HashMap<>();
        result.put("articleList", articleList);
        result.put("pageCount", pageCount);
        return AjaxResult.success(result);
    }

    @PostMapping("/showinfoaddrcount")
    public AjaxResult showInfoAddRCount(Integer id) {
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

    @PostMapping("/showinfo")
    public AjaxResult showInfo(Integer id) {
        if (id == null || id <= 0) {
            return AjaxResult.fail(-1, "illegal request");
        }
        Article article = articleService.queryArticleById(id);
        if (article == null) {
            return AjaxResult.fail(-1, "illegal request");
        }
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
        // 非空验证
        if (article == null || article.getId() == null || !StringUtils.hasLength(article.getTitle()) || !StringUtils.hasLength(article.getContent())) {
            return AjaxResult.fail(-1, "illegal parameter");
        }
        // 获取用户会话信息
        User user = UserSessionUtils.getSessionUser(request);
        if (user == null || user.getId() <= 0) {
            return AjaxResult.fail(-2, "invalid user");
        }
        // 判断文章是否存在（草稿）
        if (article.getId() != -1 && articleService.queryIdExist(article.getId())) {
            // 存在则删除
            articleService.delArticleById(article.getId(), user.getId());
        }
        // 新文章
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
    public AjaxResult showInfoListByPage(Integer pageIndex, Integer pageSize, String sortOption) {
        // 参数矫正
        if (pageIndex == null || pageIndex < 1) {
            pageIndex = 1;
        }
        if (pageSize == null || pageSize < 1 || pageSize > 5) {
            pageSize = 5;
        }
        if (!(sortOption != null && (sortOption.equals("mix") || sortOption.equals("new") || sortOption.equals("hot")))) {
            sortOption = "mix";
        }
        // 查询中偏移量(offset)的值 = (当前页码-1)*每页显示条数
        int startIndex = (pageIndex - 1) * pageSize;
        List<Article> articleList = articleService.queryArtList(pageSize, startIndex, sortOption);
        if (articleList.isEmpty()) {
            return AjaxResult.fail(-1, "Article not found");
        }
        // 限制单篇文章在文章列表页的字数并去除Markdown标记并提取纯文本
        ArticleListUtils.limitWordCountAndRemoveMarkdownTags(articleList, 120);
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
    @PostMapping("/search")
    public AjaxResult searchArticle(Integer pageIndex, Integer pageSize, String searchInfo, String sortOption) {
        // 参数矫正
        if (pageIndex == null || pageIndex < 1) {
            pageIndex = 1;
        }
        if (pageSize == null || pageSize < 1 || pageSize > 5) {
            pageSize = 5;
        }
        if (!(sortOption != null && (sortOption.equals("mix") || sortOption.equals("new") || sortOption.equals("hot")))) {
            sortOption = "mix";
        }
        if (!StringUtils.hasLength(searchInfo)) {
            return AjaxResult.fail(-1, "illegal request");
        }
        // 查询中偏移量(offset)的值 = (当前页码-1)*每页显示条数
        int startIndex = (pageIndex - 1) * pageSize;
        List<Article> articleList = articleService.queryArtListByTitle(searchInfo, pageSize, startIndex, sortOption);
        if (articleList.isEmpty()) {
            return AjaxResult.fail(-1, "No search found");
        }
        // 限制单篇文章在文章列表页的字数并去除Markdown标记并提取纯文本
        ArticleListUtils.limitWordCountAndRemoveMarkdownTags(articleList, 120);
        // 当前页面一共多少页
        // a) 查询对应文章总数
        int articleCount = articleService.queryArticleCountByTitle(searchInfo);
        // b) 页面总数 = 文章总数 / 页面显示文章数量 （同时进行进1）
        int pageCount = (int) Math.ceil(articleCount / (pageSize * 1.0));
        HashMap<String, Object> result = new HashMap<>();
        result.put("articleList", articleList);
        result.put("pageCount", pageCount);
        return AjaxResult.success(result);
    }

    @PostMapping("/savedraft")
    public AjaxResult saveDraft(HttpServletRequest request, Article article) {
        // 非空验证
        if (article == null || article.getId() == null || !StringUtils.hasLength(article.getTitle()) || !StringUtils.hasLength(article.getContent())) {
            return AjaxResult.fail(-1, "illegal parameter");
        }
        // 获取用户会话信息
        User user = UserSessionUtils.getSessionUser(request);
        if (user == null || user.getId() <= 0) {
            return AjaxResult.fail(-2, "invalid user");
        }
        // 判断是否为旧草稿
        if (article.getId() != -1 && articleService.queryIdExist(article.getId())) {
            // 旧草稿
            article.setUid(user.getId());
            return AjaxResult.success(articleService.updateArticle(article));
        }
        // 新草稿
        article.setUid(user.getId());
        article.setState(0);
        return AjaxResult.success(articleService.addDraft(article));
    }

}

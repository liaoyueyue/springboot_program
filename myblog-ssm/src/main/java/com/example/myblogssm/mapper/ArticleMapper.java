package com.example.myblogssm.mapper;

import com.example.myblogssm.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-10-14
 * Time: 15:25
 */
@Mapper
public interface ArticleMapper {

    /**
     * 使用 uid 查询文章总数
     * @param uid 用户id
     * @return uid对应用户的文章总数
     */
    int queryArticleTotalByUid(@Param("uid") Integer uid);

    /**
     * 使用 uid 查询登录用户发布文章列表
     * @param uid 用户id
     * @return uid对应用户的全部文章列表
     */
    List<Article> qryUserArtListByUid(@Param("uid") Integer uid);

    /**
     * 使用 id 删除文章
     * @param id 文章id
     * @param uid 用户id
     * @return 数据影响行数
     */
    int delArticleById(@Param("id") Integer id, @Param("uid") Integer uid);

    /**
     * 使用 id 查询文章
     * @param id 文章id
     * @return 文章实体
     */
    Article queryArticleById(@Param("id") Integer id);

    /**
     * 文章阅读量+1
     * @param id 文章id
     * @return 数据库影响行数
     */
    int updateRCount(@Param("id") Integer id);

    /**
     * 创建文章
     * @param article 文章实体
     * @return 数据库影响行数
     */
    int addArticle(Article article);

    /**
     * 修改文章
     * @param article 文章实体
     * @return 数据库影响行数
     */
    int updateArticle(Article article);

    /**
     * 分页查询文章
     * @param pageSize 页大小
     * @param startIndex 起始索引、偏移量
     * @return 指定页数的文章
     */
    List<Article> queryArtListByPage(@Param("pageSize") Integer pageSize, @Param("startIndex") Integer startIndex);

    /**
     * 获取文章总数
     * @return 文章总数
     */
    int queryArticleCount();
}

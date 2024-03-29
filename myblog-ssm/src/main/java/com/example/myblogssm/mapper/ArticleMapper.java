package com.example.myblogssm.mapper;

import com.example.myblogssm.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
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
     * @param pageSize 页大小
     * @param startIndex 起始索引、偏移量
     * @return uid对应用户的全部文章列表
     */
    List<Article> qryUserArtListByUid(@Param("uid") Integer uid, @Param("pageSize") Integer pageSize, @Param("startIndex") Integer startIndex);

    /**
     * 使用 uid 查询登录用户发布文章列表按更新时间排序
     * @param uid 用户id
     * @param pageSize 页大小
     * @param startIndex 起始索引、偏移量
     * @return uid对应用户的全部文章列表
     */
    List<Article> qryUserArtListByUidSortDate(@Param("uid") Integer uid, @Param("pageSize") Integer pageSize, @Param("startIndex") Integer startIndex);

    /**
     * 使用 uid 查询登录用户发布文章列表按阅读量排序
     * @param uid 用户id
     * @param pageSize 页大小
     * @param startIndex 起始索引、偏移量
     * @return uid对应用户的全部文章列表
     */
    List<Article> qryUserArtListByUidSortRCount(@Param("uid") Integer uid, @Param("pageSize") Integer pageSize, @Param("startIndex") Integer startIndex);

    /**
     * 使用 id 删除文章
     * @param id 文章编号
     * @param uid 用户id
     * @return 数据影响行数
     */
    int delArticleById(@Param("id") Integer id, @Param("uid") Integer uid);

    /**
     * 使用 id 查询正常状态文章
     * @param id 文章编号
     * @return 文章实体
     */
    Article queryArticleById(@Param("id") Integer id);


    /**
     * 使用 id 和 uid 来查询用户文章 - 用于查询非正常状态文章
     * @param id 文章编号
     * @param uid 用户编号
     * @return 文章实体
     */
    Article queryArticleByIdUid(Integer id, Integer uid);

    /**
     * 文章阅读量+1
     * @param id 文章编号
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
     * 定时创建文章
     * @param article 文章实体
     * @return 数据库影响行数
     */
    int addArticleSchedule(Article article);

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
    List<Article> queryArtList(@Param("pageSize") Integer pageSize, @Param("startIndex") Integer startIndex);

    /**
     * 分页查询指定文章列表
     * @param searchInfo 搜索内容
     * @param pageSize 页大小
     * @param startIndex 起始索引、偏移量
     * @return 指定页数的文章列表
     */
    List<Article> queryArtListByTitle(@Param("searchInfo") String searchInfo, @Param("pageSize") Integer pageSize, @Param("startIndex") Integer startIndex);

    /**
     * 获取文章总数
     * @return 文章总数
     */
    int queryArticleCount();

    /**
     * 获取指定文章总数
     * @param searchInfo 搜索内容
     * @return 指定文章总数
     */
    int queryArticleCountByTitle(@Param("searchInfo") String searchInfo);

    /**
     * 分页查询文章列表按更新时间排序
     * @param pageSize 页大小
     * @param startIndex 起始索引、偏移量
     * @return 符合条件文章列表
     */
    List<Article> queryArtListSortDate(Integer pageSize, Integer startIndex);

    /**
     * 分页查询文章列表按阅读量排序
     * @param pageSize 页大小
     * @param startIndex 起始索引、偏移量
     * @return 符合条件文章列表
     */
    List<Article> queryArtListSortRCount(Integer pageSize, Integer startIndex);

    /**
     * 分页查询指定文章列表按更新时间排序
     * @param searchInfo 搜索内容
     * @param pageSize 页大小
     * @param startIndex 起始索引、偏移量
     * @return 指定页数的文章列表
     */
    List<Article> queryArtListByTitleSortDate(@Param("searchInfo") String searchInfo, @Param("pageSize") Integer pageSize, @Param("startIndex") Integer startIndex);

    /**
     * 分页查询指定文章按阅读量排序
     * @param searchInfo 搜索内容
     * @param pageSize 页大小
     * @param startIndex 起始索引、偏移量
     * @return 指定页数的文章列表
     */
    List<Article> queryArtListByTitleSortRCount(@Param("searchInfo") String searchInfo, @Param("pageSize") Integer pageSize, @Param("startIndex") Integer startIndex);

    /**
     * 保存草稿文章
     * @param article 文章
     * @return 数据库影响行数
     */
    int addDraft(Article article);

    /**
     * 查询文章id是否存在
     * @param id 文章编号
     * @return 数据库影响行数
     */
    int queryIdExist(Integer id);

    /**
     * 查询需要发布的文章
     * @param currentTime 当前日期时间
     * @return 发布的文章列表
     */
    List<Article> queryNeedToPublish(LocalDateTime currentTime);

    /**
     * 修改文章状态
     * @param id 文章编号
     * @param state 文章状态
     * @return 数据库影响行数
     */
    int updateState(Integer id, Integer state);

    
}

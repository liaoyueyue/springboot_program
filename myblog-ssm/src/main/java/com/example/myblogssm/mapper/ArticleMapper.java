package com.example.myblogssm.mapper;

import com.example.myblogssm.entity.Article;
import com.example.myblogssm.entity.User;
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



}

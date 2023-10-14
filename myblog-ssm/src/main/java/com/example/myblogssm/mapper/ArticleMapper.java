package com.example.myblogssm.mapper;

import com.example.myblogssm.entity.Article;
import com.example.myblogssm.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * @return uid 对应用户的文章总数
     */
    int queryArticleTotalByUid(@Param("uid") int uid);



    /**
     * 添加文章
     * @param article 文章实体
     * @param user 用户实体
     * @return 数据影响行数
     */
    int addArticle(@Param("articleInfo") Article article, @Param("userInfo") User user);

}

package com.example.myblogssm.mapper;

import com.example.myblogssm.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-11-24
 * Time: 23:03
 */
@Mapper
public interface CommentMapper {
    /**
     * 根据文章 id 来查询评论列表
     * @param aid 文章 id
     * @return 返回对应文章的评论列表
     */
    List<Comment> queryCommentListByAid(@Param("aid") Integer aid);

    /**
     * 根据文章 id 查询文章评论总数
     * @param aid 文章 id
     * @return 返回对应文章的评论列表总数
     */
    int queryCommentTotalByAid(@Param("aid") Integer aid);
}

package com.example.myblogssm.service;

import com.example.myblogssm.entity.Comment;
import com.example.myblogssm.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-11-24
 * Time: 23:06
 */
@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;

    public List<Comment> queryCommentListByAid(Integer aid) {
        return commentMapper.queryCommentListByAid(aid);
    }

    public int queryCommentTotalByAid(Integer aid) {
        return commentMapper.queryCommentTotalByAid(aid);
    }

    public int addComment(Integer aid, Integer uid, String ctext) {
        return commentMapper.addComment(aid, uid, ctext);
    }
}

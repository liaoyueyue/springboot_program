package com.example.myblogssm.controller;

import com.example.myblogssm.common.AjaxResult;
import com.example.myblogssm.entity.Comment;
import com.example.myblogssm.entity.User;
import com.example.myblogssm.entity.vo.CommentVo;
import com.example.myblogssm.service.CommentService;
import com.example.myblogssm.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-11-24
 * Time: 23:07
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;
    @RequestMapping("/showinfobyid")
    public AjaxResult showInfoById(Integer aid) {
        // 1. 非空验证
        if (aid == null || aid <= 0) {
            return AjaxResult.fail(-1, "illegal request");
        }
        // 2.查询对应文章评论
        List<Comment> commentList =  commentService.queryCommentListByAid(aid);
        if (commentList.isEmpty()) {
            return AjaxResult.fail(-1, "Comment not found");
        }
        int commentCount = commentService.queryCommentTotalByAid(aid);
        // 3.使用 CommentVO 来接受评论用户的用户名和头像
        List<CommentVo> commentVoList = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentVo commentVo = new CommentVo();
            BeanUtils.copyProperties(comment, commentVo);
            User user = userService.queryUserById(comment.getUid());
            commentVo.setUserName(user.getUsername());
            commentVo.setUserPhoto(user.getPhoto());
            commentVoList.add(commentVo);
        }
        HashMap<String, Object> result = new HashMap<>();
        result.put("commentList", commentVoList);
        result.put("commentCount", commentCount);
        return AjaxResult.success(result);
    }

}

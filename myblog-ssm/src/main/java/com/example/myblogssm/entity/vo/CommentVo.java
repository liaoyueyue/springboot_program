package com.example.myblogssm.entity.vo;

import com.example.myblogssm.entity.Comment;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-11-24
 * Time: 23:52
 */
@Data
public class CommentVo extends Comment {
    private String userName;
    private String userPhoto;
    @Override
    public String toString() {
        return super.toString() + " | CommentVo{" +
                "userName=" + userName +
                "userPhoto=" + userPhoto +
                '}';
    }
}

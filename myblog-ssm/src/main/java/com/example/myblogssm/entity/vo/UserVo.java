package com.example.myblogssm.entity.vo;

import com.example.myblogssm.entity.User;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-10-15
 * Time: 0:26
 */
@Data
public class UserVo extends User {
    private int articleTotal;

    @Override
    public String toString() {
        return super.toString() + " | UserVo{" +
                "articleTotal=" + articleTotal +
                '}';
    }
}

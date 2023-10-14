package com.example.myblogssm.service;

import com.example.myblogssm.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-10-14
 * Time: 15:31
 */
@Service
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    public int queryArticleTotalByUid(int uid) {
        return articleMapper.queryArticleTotalByUid(uid);
    }
}

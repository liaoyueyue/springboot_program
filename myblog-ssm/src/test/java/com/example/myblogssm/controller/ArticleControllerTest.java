package com.example.myblogssm.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-11-12
 * Time: 2:22
 */
@SpringBootTest
class ArticleControllerTest {
    @Autowired
    ArticleController articleController;
    @Test
    void showInfoListByPage() {
        Integer pageIndex = 1;
        Integer pageSize = 4;
        String selectedOption = "new";
        articleController.showInfoListByPage(pageIndex, pageSize, selectedOption);
    }
}
package com.example.myblogssm.common.utils;

import com.example.myblogssm.entity.Article;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.text.TextContentRenderer;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-11-07
 * Time: 23:16
 */
public class ArticleListUtils {
    /**
     * 限制单篇文章在文章列表页的字数并去除Markdown标记并提取纯文本
     * @param articleList 文章列表
     * @param count 单片文章限制字数
     */
    public static void limitWordCountAndRemoveMarkdownTags(List<Article> articleList, int count){
        for (Article a : articleList) {
            String content = removeMarkdownTags(a.getContent());
            if (content.length() > count) {
                a.setContent(content.substring(0, count) + "...");
            } else {
                a.setContent(content);
            }
        }
    }

    /**
     * 去除Markdown标记并提取纯文本
     * @param markdownText Markdown文本
     * @return 纯文本
     */
    public static String removeMarkdownTags(String markdownText) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdownText);
        TextContentRenderer textRenderer = TextContentRenderer.builder().build();
        return textRenderer.render(document);
    }
}

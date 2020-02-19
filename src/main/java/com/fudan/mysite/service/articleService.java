package com.fudan.mysite.service;

import com.fudan.mysite.entity.Article;

import java.util.List;

public interface articleService {
    void saveArticle(Article article);

    List<Article> findAll();
}

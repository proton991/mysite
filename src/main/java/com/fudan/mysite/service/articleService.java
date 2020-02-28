package com.fudan.mysite.service;

import com.fudan.mysite.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface articleService {
    void saveArticle(Article article);

    List<Article> findAll();

    Article findArticleByArticleId(Integer id);

    void deleteArticle(Integer id);

    Page<Article> findArticleNoCriteria(Integer page, Integer size);

    Page<Article> findBookCriteria(Specification<Article> specification, Pageable pageable);
}

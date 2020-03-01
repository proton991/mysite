package com.fudan.mysite.dao;

import com.fudan.mysite.entity.Article;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleDao extends JpaRepository<Article, Integer> {
    Article findArticleByArticleId(Integer id);

    Page<Article> findAll(Specification<Article> specification, Pageable pageable);
//    void deleteByArticleId(Integer id);

    List<Article> findArticlesByTitleContaining(String keyword);
}

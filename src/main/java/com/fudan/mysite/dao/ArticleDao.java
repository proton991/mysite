package com.fudan.mysite.dao;

import com.fudan.mysite.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleDao extends JpaRepository<Article, Integer> {

}

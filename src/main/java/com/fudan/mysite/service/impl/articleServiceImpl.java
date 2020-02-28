package com.fudan.mysite.service.impl;

import com.fudan.mysite.dao.ArticleDao;
import com.fudan.mysite.entity.Article;
import com.fudan.mysite.service.articleService;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class articleServiceImpl implements articleService {

    @Resource
    private ArticleDao articleDao;

    @Override
    public void saveArticle(Article article) {
        articleDao.save(article);
    }

    @Override
    public List<Article> findAll() {
        return articleDao.findAll();
    }

    @Override
    public Article findArticleByArticleId(Integer id) {
        return articleDao.findArticleByArticleId(id);
    }

    @Override
    public void deleteArticle(Integer id) {
        articleDao.deleteById(id);
    }

    @Override
    public Page<Article> findArticleNoCriteria(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return articleDao.findAll(pageable);
    }

    @Override
    public Page<Article> findBookCriteria(Specification<Article> specification, Pageable pageable) {
        return articleDao.findAll(specification, pageable);
    }

}

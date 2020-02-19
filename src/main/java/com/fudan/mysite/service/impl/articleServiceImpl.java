package com.fudan.mysite.service.impl;

import com.fudan.mysite.dao.ArticleDao;
import com.fudan.mysite.entity.Article;
import com.fudan.mysite.service.articleService;
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
}

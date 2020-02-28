package com.fudan.mysite.service.impl;

import com.fudan.mysite.dao.ArticleExtraDao;
import com.fudan.mysite.entity.ArticleExtra;
import com.fudan.mysite.service.articleExtraService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class articleExtraServiceImpl implements articleExtraService {

    @Resource
    private ArticleExtraDao articleExtraDao;
    @Override
    public void saveExtra(ArticleExtra extra) {
        articleExtraDao.save(extra);
    }
}

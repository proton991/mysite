package com.fudan.mysite.controller;

import com.fudan.mysite.base.controller.BaseApiController;
import com.fudan.mysite.base.controller.BaseController;
import com.fudan.mysite.base.result.Result;
import com.fudan.mysite.base.result.ResultCode;
import com.fudan.mysite.entity.Article;
import com.fudan.mysite.entity.RBAC.UserInfo;
import com.fudan.mysite.entity.UserProfile;
import com.fudan.mysite.service.articleService;
import com.fudan.mysite.service.userInfoService;
import com.fudan.mysite.service.userProfileService;
import com.fudan.mysite.util.RedisUtil;
import com.fudan.mysite.vo.ArticleVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class ArticleController extends BaseController{

    @Resource
    private articleService articleService;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private userInfoService userInfoService;

    @Resource
    private userProfileService userProfileService;

    @RequestMapping(value = "/postArticle", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Result<Object> postArticle(@RequestBody ArticleVO articleVO) {
        String username = articleVO.getAuthor();
        if (redisUtil.get(username) == null)
            return Result.failure(ResultCode.NOT_LOGIN);
        UserInfo userInfo = userInfoService.findUserByName(username);
        UserProfile userProfile = userInfo.getUserProfile();
        Set<UserProfile> authors = new HashSet<>();
        authors.add(userProfile);
        Article article = new Article();
        article.setBodyHtml(articleVO.getBodyHtml());
        article.setBodyMd(articleVO.getBodyMd());
        article.setAuthors(authors);
        article.setTitle(articleVO.getTitle());
        article.setState(articleVO.getState());
        //userProfile changes
        Set<Article> articles = new HashSet<>();
        articles.add(article);
        userProfile.setArticles(articles);
        articleService.saveArticle(article);
        userProfileService.saveProfile(userProfile);
        return Result.success();
    }

    @RequestMapping(value = "/getArticles", method = RequestMethod.GET)
    public Result<Object> getArticleList() {
        List<Article> articleList = articleService.findAll();
        return Result.success(articleList);
    }
}

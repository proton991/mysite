package com.fudan.mysite.controller;

import com.alibaba.fastjson.JSONObject;
import com.fudan.mysite.base.controller.BaseApiController;
import com.fudan.mysite.base.result.Result;
import com.fudan.mysite.base.result.ResultCode;
import com.fudan.mysite.entity.Article;
import com.fudan.mysite.entity.ArticleExtra;
import com.fudan.mysite.entity.Category;
import com.fudan.mysite.entity.RBAC.UserInfo;
import com.fudan.mysite.entity.UserProfile;
import com.fudan.mysite.service.*;
import com.fudan.mysite.util.RedisUtil;
import com.fudan.mysite.vo.ArticleVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@RestController
public class ArticleController extends BaseApiController{

    @Resource
    private articleExtraService articleExtraService;
    @Resource
    private articleService articleService;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private categoryService categoryService;

    @Resource
    private userInfoService userInfoService;

    @Resource
    private userProfileService userProfileService;

    @RequestMapping(value = "/postArticle", consumes = "application/json; charset=UTF-8")
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
        article.setUpdateTime(new Date().getTime());
        article.setViews(0);
        ArticleExtra extra = new ArticleExtra();
        extra.setViews(0);
        extra.setCategory(categoryService.findCategoryByCategoryId(articleVO.getCategoryId()));
        articleExtraService.saveExtra(extra);
        article.setExtra(extra);

        //userProfile changes
        Set<Article> articles;
        if (userProfile.getArticles() == null)
            articles = new HashSet<>();
        else
            articles = userProfile.getArticles();
        articles.add(article);
        userProfile.setArticles(articles);

        articleService.saveArticle(article);
        userProfileService.saveProfile(userProfile);

        return Result.success();
    }

    @RequestMapping(value = "/articles")
    public Result<Object> getArticleList() {
        List<Article> articleList = articleService.findAll();
        return Result.success(articleList);
    }

    @RequestMapping(value = "/articles/{id}")
    public Result<Object> getArticleById(@PathVariable("id") Integer id) {
        Article article = articleService.findArticleByArticleId(id);
        if (article != null) {
            ArticleExtra extra = article.getExtra();
            Integer origin = extra.getViews();
            article.setViews(origin + 1);
            extra.setViews(origin + 1);
            articleExtraService.saveExtra(extra);
            return Result.success(article);
        }
        else
            return Result.failure(ResultCode.ARTICLE_NOT_EXIST);
    }

    @RequestMapping(value = "/edit/article/{id}", consumes = "application/json; charset=UTF-8")
    public Result<Object> editArticle(@PathVariable("id") Integer id, @RequestBody ArticleVO articleVO) {
        Article article = articleService.findArticleByArticleId(id);
        if (article != null) {
            article.setTitle(articleVO.getTitle());
            article.setBodyMd(articleVO.getBodyMd());
            article.setBodyHtml(articleVO.getBodyHtml());
            article.setUpdateTime(new Date().getTime());
            ArticleExtra extra = article.getExtra();
            extra.setCategory(categoryService.findCategoryByCategoryId(articleVO.getCategoryId()));
            articleExtraService.saveExtra(extra);
            article.setExtra(extra);
            articleService.saveArticle(article);
            System.out.println("Edition finished Successfully");
            return Result.success();
        }
        return Result.failure();
    }

    @RequestMapping(value = "/delete/article/{id}")
    public Result<Object> deleteArticle(@PathVariable("id") Integer id) {
        String username = "cxy";
        Article article = articleService.findArticleByArticleId(id);
        UserProfile userProfile = userProfileService.findByUsername(username);
        Set<Article> articles = userProfile.getArticles();
        articles.remove(article);
        userProfile.setArticles(articles);
        userProfileService.saveProfile(userProfile);
        articleService.deleteArticle(id);
        System.out.println("delete finished");
        return Result.success();
    }

    @RequestMapping(value = "/articleList")
    public Result<Object> getArticlePages(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<Article> articlePage = articleService.findArticleNoCriteria(page - 1, size);
        JSONObject result = new JSONObject();
        Map<String, Object> map = new HashMap<>();
        result.put("articles", articlePage.getContent());
        result.put("totalPages", articlePage.getTotalPages());
        return Result.success(result);
    }

    @RequestMapping(value = "/articleCategory/edit")
    public Result<Object> editArticleCategory(@RequestParam("id") Integer id, @RequestParam("category") String newName) {
        Category category = categoryService.findCategoryByCategoryId(id);
        category.setCategoryName(newName);
        categoryService.saveCategory(category);
        return Result.success();
    }
    @RequestMapping(value = "/articleCategory/delete")
    public Result<Object> deleteArticleCategory(@RequestParam("id") Integer id) {
        Category category = categoryService.findCategoryByCategoryId(id);
        categoryService.saveCategory(category);
        if (category.getArticleExtras().size() != 0)
            return Result.failure(ResultCode.CATEGORY_NOT_NULL);
        categoryService.deleteByCategoryId(id);
        return Result.success();
    }
    @RequestMapping(value = "/articleCategory/set")
    public Result<Object> setArticleCategory(@RequestParam("id") Integer id, @RequestParam("category") String name) {
        Article article = articleService.findArticleByArticleId(id);
        ArticleExtra extra = article.getExtra();
        Category category = categoryService.findCategoryByCategoryName(name);
        extra.setCategory(category);
        article.setExtra(extra);
        articleExtraService.saveExtra(extra);
        articleService.saveArticle(article);
        categoryService.saveCategory(category);
        return Result.success();
    }

    @RequestMapping(value = "/articleCategory/new")
    public Result<Object> newArticleCategory(@RequestParam("name") String name) {
        Category category = new Category();
        category.setCategoryName(name);
        categoryService.saveCategory(category);
        return Result.success();
    }

    @RequestMapping(value = "/articleCategory/get")
    public Result<Object> getArticleCategories() {
        return Result.success(categoryService.findAll());
    }

    @RequestMapping(value = "/articleListByCtg")
    public Result<Object> filterArticlesByCategory(@RequestParam("categoryId") Integer id,
                                                   @RequestParam("page") Integer page,
                                                   @RequestParam("size") Integer size)
    {
        Specification<Article> queryCondition = new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (id != null) {
                    predicateList.add(criteriaBuilder.equal(root.get("extra").get("category").get("categoryId"), id));
                }

                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
        JSONObject res = new JSONObject();
        if (page < 1)
            page = 1;
        if (size < 1)
            size = 1;
        Page<Article> articlePage = articleService.findBookCriteria(queryCondition, PageRequest.of(page - 1, size));
        res.put("articles", articlePage.getContent());
        res.put("totalPages", articlePage.getTotalPages());
        return Result.success(res);
//
//        return Result.success(articles);
    }
}

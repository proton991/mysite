package com.fudan.mysite.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class ArticleExtra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "aex_id")
    private Integer aex_Id;

    @Column(name = "views")
    private Integer views;

    @JsonIgnore
    @OneToOne(mappedBy = "extra")
    private Article article;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Integer getAex_Id() {
        return aex_Id;
    }

    public void setAex_Id(Integer aex_Id) {
        this.aex_Id = aex_Id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }
}

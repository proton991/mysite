package com.fudan.mysite.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "category_name")
    private String categoryName;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<ArticleExtra> getArticleExtras() {
        return articleExtras;
    }

    public void setArticleExtras(Set<ArticleExtra> articleExtras) {
        this.articleExtras = articleExtras;
    }

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<ArticleExtra> articleExtras;
}

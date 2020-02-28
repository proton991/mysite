package com.fudan.mysite.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

//@JsonIgnoreProperties(value = {"authors"})
@Entity
@Table(name = "article")
@EntityListeners(AuditingEntityListener.class)
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "article_id")
    private Integer articleId;


    @Column(name = "title")
    private String title;

    @Column(name = "state")
    private String state;

    @Column(name = "views")
    private Integer views;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "longtext", name = "body_md")
    private String bodyMd;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "longtext", name = "body_html")
    private String bodyHtml;

    public Set<UserProfile> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<UserProfile> authors) {
        this.authors = authors;
    }

    @CreatedDate
    @Column(name = "create_date", updatable = false, nullable = false)
    private Long createTime;

    @Column(name = "update_time", nullable = false)
    private Long updateTime;

    @ManyToMany(mappedBy = "articles", cascade = CascadeType.REMOVE)
    private Set<UserProfile> authors;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "aex_id")
    private ArticleExtra extra;

    public ArticleExtra getExtra() {
        return extra;
    }

    public void setExtra(ArticleExtra extra) {
        this.extra = extra;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getBodyMd() {
        return bodyMd;
    }

    public void setBodyMd(String bodyMd) {
        this.bodyMd = bodyMd;
    }

    public String getBodyHtml() {
        return bodyHtml;
    }

    public void setBodyHtml(String bodyHtml) {
        this.bodyHtml = bodyHtml;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    @Override
    public String toString() {
        return String.valueOf(articleId);
    }
}

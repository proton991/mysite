package com.fudan.mysite.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fudan.mysite.entity.RBAC.UserInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

//@JsonIgnoreProperties(value = {"articles", "profileId"})
@Entity
@Table(name = "user_profile")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "profile_id")
    private Integer profileId;

    @JsonIgnore
    @OneToOne(mappedBy = "userProfile")
    private UserInfo userInfo;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "text", nullable = true)
    private String introduction;

    @JsonIgnore
    @ManyToMany
    @JoinColumn(name = "article_id")
    public Set<Article> articles;

    @Column(name = "username")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "email")
    private String email;

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return String.valueOf(profileId);
    }
}

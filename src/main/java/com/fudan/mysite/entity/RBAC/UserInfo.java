package com.fudan.mysite.entity.RBAC;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fudan.mysite.entity.UserProfile;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "user_info")
public class UserInfo implements Serializable {

    @Id
    @Column(name = "uid")
    public String getUid() {
        return uid;
    }

    public void setUid(String userId) {
        this.uid = userId;
    }


    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "profile_id")
    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @Column(name = "state")
    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sys_user_role", joinColumns = { @JoinColumn(name = "uid")},
            inverseJoinColumns = {@JoinColumn(name = "rid")})
    public Set<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(Set<SysRole> roleList) {
        this.roleList = roleList;
    }

    @Column(name = "username", nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String uid;

    private String username;

    private String password;

    private UserProfile userProfile;

    private Byte state = 1; //用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定

    private Set<SysRole> roleList;

    @Override
    public String toString() {
        return uid;
    }
}

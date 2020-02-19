package com.fudan.mysite.entity.RBAC;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="sys_role")
public class SysRole implements Serializable {
    private Integer rid;
    private String role;
    private Set<SysPermission> permissions;
    private Set<UserInfo> userInfoList;

    @ManyToMany(mappedBy = "roleList")
    public Set<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(Set<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sys_role_permission", joinColumns = {@JoinColumn(name = "rid")},
            inverseJoinColumns = {@JoinColumn(name = "pid")})
    public Set<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<SysPermission> permissions) {
        this.permissions = permissions;
    }

    @Id
    @GeneratedValue
    @Column(name = "rid")
    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    @Column(name = "role", nullable = false)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}

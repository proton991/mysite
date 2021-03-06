package com.fudan.mysite.entity.RBAC;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "sys_permission")
public class SysPermission implements Serializable {
    private Integer pid;

    private String permission;

    private Set<SysRole> roleList;

    @ManyToMany(mappedBy = "permissions")
    public Set<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(Set<SysRole> roleList) {
        this.roleList = roleList;
    }

    @Id
    @GeneratedValue
    @Column(name = "pid")
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Column(name = "permission")
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}

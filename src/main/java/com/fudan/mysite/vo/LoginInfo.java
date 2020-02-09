package com.fudan.mysite.vo;

import java.io.Serializable;
import java.util.List;

public class LoginInfo implements Serializable {

    private String username;


    private List<String> roleList;

    private List<String> permissionList;

    public LoginInfo() {

    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }

    public List<String> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<String> permissionList) {
        this.permissionList = permissionList;
    }
}

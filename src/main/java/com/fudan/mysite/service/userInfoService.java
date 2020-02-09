package com.fudan.mysite.service;

import com.fudan.mysite.entity.RBAC.UserInfo;

public interface userInfoService {
    public UserInfo findUserByName(String username);

    void saveUserInfo(UserInfo userInfo);
}

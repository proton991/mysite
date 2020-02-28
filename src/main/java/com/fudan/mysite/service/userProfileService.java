package com.fudan.mysite.service;

import com.fudan.mysite.entity.RBAC.UserInfo;
import com.fudan.mysite.entity.UserProfile;

public interface userProfileService {
    void saveProfile(UserProfile userProfile);

    UserProfile findByUserInfo(UserInfo userInfo);

    UserProfile findByUsername(String username);
}

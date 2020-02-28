package com.fudan.mysite.service.impl;

import com.fudan.mysite.dao.UserProfileDao;
import com.fudan.mysite.entity.RBAC.UserInfo;
import com.fudan.mysite.entity.UserProfile;
import com.fudan.mysite.service.userProfileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class userProfileServiceImpl implements userProfileService {
    @Resource
    private UserProfileDao userProfileDao;
    @Override
    public void saveProfile(UserProfile userProfile) {
        userProfileDao.save(userProfile);
    }

    @Override
    public UserProfile findByUserInfo(UserInfo userInfo) {
        return userProfileDao.findByUserInfo(userInfo);
    }

    @Override
    public UserProfile findByUsername(String username) {
        return userProfileDao.findByUsername(username);
    }
}

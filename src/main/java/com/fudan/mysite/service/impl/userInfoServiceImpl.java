package com.fudan.mysite.service.impl;

import com.fudan.mysite.dao.UserInfoDao;
import com.fudan.mysite.entity.RBAC.UserInfo;
import com.fudan.mysite.service.userInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class userInfoServiceImpl implements userInfoService {
    @Resource
    private UserInfoDao userInfoDao;
    @Override
    public UserInfo findUserByName(String username) {
        return userInfoDao.findUserInfoByUsername(username);
    }

    @Override
    public void saveUserInfo(UserInfo userInfo) {
        userInfoDao.save(userInfo);
    }
}

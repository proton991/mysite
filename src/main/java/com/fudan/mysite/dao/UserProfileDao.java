package com.fudan.mysite.dao;

import com.fudan.mysite.entity.RBAC.UserInfo;
import com.fudan.mysite.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileDao extends JpaRepository<UserProfile, Integer> {

    UserProfile findByUserInfo(UserInfo userInfo);

    UserProfile findByUsername(String username);
}

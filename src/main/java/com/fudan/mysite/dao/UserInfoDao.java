package com.fudan.mysite.dao;

import com.fudan.mysite.entity.RBAC.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoDao extends JpaRepository<UserInfo, String> {
    public UserInfo findUserInfoByUsername(String username);
}

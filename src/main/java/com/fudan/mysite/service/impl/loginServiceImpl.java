package com.fudan.mysite.service.impl;

import com.fudan.mysite.dao.UserInfoDao;
import com.fudan.mysite.entity.RBAC.SysPermission;
import com.fudan.mysite.entity.RBAC.SysRole;
import com.fudan.mysite.entity.RBAC.UserInfo;
import com.fudan.mysite.service.loginService;
import com.fudan.mysite.service.userInfoService;
import com.fudan.mysite.util.RedisUtil;
import com.fudan.mysite.vo.LoginInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class loginServiceImpl implements loginService {
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private UserInfoDao userInfoDao;
    @Override
    public LoginInfo getLoginInfo(String username) {
        UserInfo userInfo = userInfoDao.findUserInfoByUsername(username);
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUsername(username);
        Set<SysRole> roleList = userInfo.getRoleList();
        Set<String> roleStrList = new HashSet<>();
        Set<String> permissionsStr = new HashSet<>();
        for (SysRole role : roleList) {
            roleStrList.add(role.getRole());
            Set<SysPermission> tmp = role.getPermissions();
            for (SysPermission permission: tmp) {
                permissionsStr.add(permission.getPermission());
            }
        }
        loginInfo.setRoleList(roleStrList);
        loginInfo.setPermissionList(permissionsStr);
        String token = redisUtil.get(username);
        loginInfo.setToken(token);
        return loginInfo;
    }
}

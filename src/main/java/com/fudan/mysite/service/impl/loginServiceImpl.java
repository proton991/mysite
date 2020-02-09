package com.fudan.mysite.service.impl;

import com.fudan.mysite.dao.UserInfoDao;
import com.fudan.mysite.entity.RBAC.SysPermission;
import com.fudan.mysite.entity.RBAC.SysRole;
import com.fudan.mysite.entity.RBAC.UserInfo;
import com.fudan.mysite.service.loginService;
import com.fudan.mysite.service.userInfoService;
import com.fudan.mysite.vo.LoginInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class loginServiceImpl implements loginService {
    @Resource
    private UserInfoDao userInfoDao;
    @Override
    public LoginInfo getLoginInfo(String username) {
        UserInfo userInfo = userInfoDao.findUserInfoByUsername(username);
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUsername(username);
        List<SysRole> roleList = userInfo.getRoleList();
        List<String> roleStrList = new ArrayList<>();
        List<String> permissionsStr = new ArrayList<>();
        for (SysRole role : roleList) {
            roleStrList.add(role.getRole());
            List<SysPermission> tmp = role.getPermissions();
            for (SysPermission permission: tmp) {
                permissionsStr.add(permission.getPermission());
            }
        }
        loginInfo.setRoleList(roleStrList);
        loginInfo.setPermissionList(permissionsStr);
        return loginInfo;
    }
}

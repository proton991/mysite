package com.fudan.mysite.config;
import com.fudan.mysite.service.userInfoService;
import com.fudan.mysite.entity.RBAC.SysPermission;
import com.fudan.mysite.entity.RBAC.SysRole;
import com.fudan.mysite.entity.RBAC.UserInfo;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;

import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private userInfoService userInfoService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = (String) principalCollection.getPrimaryPrincipal();
        UserInfo userInfo = userInfoService.findUserByName(username);
        for (SysRole role : userInfo.getRoleList()) {
            authorizationInfo.addRole(role.getRole());
            for (SysPermission permission : role.getPermissions()) {
                authorizationInfo.addStringPermission(permission.getPermission());
            }
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        UserInfo userInfo = userInfoService.findUserByName(username);
        if (userInfo == null)
            return null;
        System.out.println(userInfo.getPassword());
        Object principal = userInfo.getUsername();
        Object credentials = userInfo.getPassword();
        ByteSource credentialsSalt = ByteSource.Util.bytes(principal);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                principal,
                credentials,
                credentialsSalt,
                getName()
        );
        return authenticationInfo;
    }
}

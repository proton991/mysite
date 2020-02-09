package com.fudan.mysite.service.impl;

import com.fudan.mysite.dao.SysRoleDao;
import com.fudan.mysite.entity.RBAC.SysRole;
import com.fudan.mysite.service.sysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class sysRoleServiceImpl implements sysRoleService {
    @Resource
    private SysRoleDao sysRoleDao;
    @Override
    public SysRole findSysRoleByRole(String role) {
        return sysRoleDao.findSysRoleByRole(role);
    }
}

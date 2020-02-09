package com.fudan.mysite.service;

import com.fudan.mysite.entity.RBAC.SysRole;

public interface sysRoleService {
    SysRole findSysRoleByRole(String role);
}

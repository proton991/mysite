package com.fudan.mysite.dao;

import com.fudan.mysite.entity.RBAC.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysRoleDao extends JpaRepository<SysRole, Integer> {
    SysRole findSysRoleByRole(String role);
}

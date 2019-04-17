package com.bdqn.ssm.dao;

import com.bdqn.ssm.entity.Role;

import java.util.List;

public interface RoleDao {
    //查询所有角色信息
    public List<Role> getRoleList();
}

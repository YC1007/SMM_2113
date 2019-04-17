package com.bdqn.ssm.service;

import com.bdqn.ssm.entity.Role;

import java.util.List;

public interface RoleService {
    //查询所有角色信息
    public List<Role> getRoleList();
}

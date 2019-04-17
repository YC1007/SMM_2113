package com.bdqn.ssm.service;

import com.bdqn.ssm.entity.Provider;

import java.util.List;

public interface ProviderService {
    //查询所有供应商信息
    public List<Provider> getProviderList();
}

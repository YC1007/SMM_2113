package com.bdqn.ssm.dao;



import com.bdqn.ssm.entity.Provider;

import java.util.List;

public interface ProviderDao {
    //查询所有供应商信息
    public List<Provider> getProviderList();
}

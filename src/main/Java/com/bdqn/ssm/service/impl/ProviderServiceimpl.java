package com.bdqn.ssm.service.impl;

import com.bdqn.ssm.dao.ProviderDao;
import com.bdqn.ssm.entity.Provider;
import com.bdqn.ssm.service.ProviderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("providerService")
public class ProviderServiceimpl implements ProviderService {
    @Resource
    private ProviderDao pd;
    public ProviderDao getPd() {
        return pd;
    }
    public void setPd(ProviderDao pd) {
        this.pd = pd;
    }

    @Override
    public List<Provider> getProviderList() {
        return pd.getProviderList();
    }
}

package org.jsf.core.service.impl;

import org.jsf.core.service.MulitInstanceService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Service;

@Service
public class MulitInstanceServiceFactoryBean implements FactoryBean<MulitInstanceService>{
    public MulitInstanceService getObject() throws Exception {
        System.out.println("factoryBean");
        return new MulitInstanceServiceImpl();
    }

    public Class<?> getObjectType() {
        return MulitInstanceService.class;
    }

    public boolean isSingleton() {
        return true;
    }
}

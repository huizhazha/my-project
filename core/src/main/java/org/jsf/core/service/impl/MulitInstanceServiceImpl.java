package org.jsf.core.service.impl;

import org.jsf.core.service.MulitInstanceService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

//@Service
//@Scope("prototype")
public class MulitInstanceServiceImpl implements MulitInstanceService{
    private String testStr;

    public void test() {
        System.out.println(testStr);
        
    }
    
    public void changeValue(String value) {
        setTestStr(value);
        
    }

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }
}

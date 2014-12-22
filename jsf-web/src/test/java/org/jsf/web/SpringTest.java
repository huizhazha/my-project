package org.jsf.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.my.core.service.MulitInstanceService;
import org.my.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext*.xml")
public class SpringTest {
    @Autowired
    private UserService userService;
    
    @Autowired
    private MulitInstanceService mulitInstanceService;
    
    @Test
    public void getUser(){
        System.out.println(userService.getUser("username"));
    }
    
    @Test
    public void instanceTest(){
        mulitInstanceService.test();
        mulitInstanceService.changeValue("changed");
        mulitInstanceService.test();
    }
}

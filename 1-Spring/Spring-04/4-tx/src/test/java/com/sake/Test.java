package com.sake;

import com.sake.service.IAccountService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class Test {
    @Autowired
    private IAccountService as;

    @org.junit.Test
    public void test(){
        as.transfer("aaa","bbb",100f);
    }

}

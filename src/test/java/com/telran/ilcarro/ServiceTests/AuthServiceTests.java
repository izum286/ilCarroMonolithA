package com.telran.ilcarro.ServiceTests;

import com.telran.ilcarro.config.TestsConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestsConfig.class})
public class AuthServiceTests {

    @Before
    public void setUp(){

    }

    @After
    public void afterTest(){

    }

    @Test
    public void testAuthRegistration(){

    }

}

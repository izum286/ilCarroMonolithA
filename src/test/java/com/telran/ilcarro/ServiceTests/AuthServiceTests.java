package com.telran.ilcarro.ServiceTests;

import com.telran.ilcarro.config.TestsConfig;
import com.telran.ilcarro.service.auth.AuthService;
import com.telran.ilcarro.service.user.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestsConfig.class})
public class AuthServiceTests {

    //UserEmail: junittester@mail.com
    //Password: junittester
    //Token Base64: anVuaXR0ZXN0ZXJAbWFpbC5jb206anVuaXR0ZXN0ZXI=

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @Before
    public void setUp(){
//        if(userService.getUser("junittester@mail.com").isPresent()){
//            userService.deleteUser("junittester@mail.com");
//        }
    }

    @After
    public void afterTest(){
//        if(userService.getUser("junittester@mail.com").isPresent()){
//            userService.deleteUser("junittester@mail.com");
//        }
    }

    @Test
    public void testAuthRegistration(){
        assertEquals(authService.registration("anVuaXR0ZXN0ZXJAbWFpbC5jb206anVuaXR0ZXN0ZXI="),"junittester@mail.com");
    }

    @Test
    public void testAuthUpdatePassword(){
        assertEquals(authService.updatePassword("junittester@mail.com","notjunittester"),"junittester@mail.com");
    }

    @Test
    public void testAuthUpdatePasswordWithEmailNullParam(){
        assertNotNull(authService.updatePassword(null,"notjunittester"));
    }

    @Test
    public void testAuthUpdatePasswordWithPasswordNullParam(){
        assertNotNull(authService.updatePassword("junittester@mail.com",null));
    }

    //Need check
    @Test
    public void testAuthUpdatePasswordWithAllNullParams(){
        assertNotNull(authService.updatePassword(null,null));
    }

    @Test
    public void testAuthRegistrationWithNullToken(){
        assertNotNull(authService.registration(null));
    }

}

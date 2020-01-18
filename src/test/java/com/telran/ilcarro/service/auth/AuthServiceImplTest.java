package com.telran.ilcarro.service.auth;

import com.telran.ilcarro.service.exceptions.ServiceException;
import com.telran.ilcarro.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class AuthServiceImplTest {

    //UserEmail: junittester@mail.com
    //Password: junittester
    //Token Base64: anVuaXR0ZXN0ZXJAbWFpbC5jb206anVuaXR0ZXN0ZXI=

    @MockBean
    AuthService authService;

    @MockBean
    UserService userService;

    @Test
    void registration() {
        assertEquals(authService.registration("anVuaXR0ZXN0ZXJAbWFpbC5jb206anVuaXR0ZXN0ZXI="),"junittester@mail.com");
    }

    @Test
    void updatePassword() {
        assertEquals(authService.updatePassword("junittester@mail.com","notjunittester"),"junittester@mail.com");
    }

    @Test()
    public void testAuthUpdatePasswordWithEmailNullParam(){
        authService.updatePassword(null, "notjunittester");
    }

    @Test
    public void testAuthUpdatePasswordWithPasswordNullParam(){
        assertNotNull(authService.updatePassword("junittester@mail.com",null));
    }
}
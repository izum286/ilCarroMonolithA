package com.telran.ilcarro.service.auth;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class TokenServiceImplTest {

    //UserEmail: junittester@mail.com
    //Password: junittester
    //Token Base64: anVuaXR0ZXN0ZXJAbWFpbC5jb206anVuaXR0ZXN0ZXI=

    @MockBean
    TokenService tokenService;

    @Test
    void decodeToken() {
        String[] arr = tokenService.decodePassword("anVuaXR0ZXN0ZXJAbWFpbC5jb206anVuaXR0ZXN0ZXI=").split(":");
        assertEquals(arr[0],"junittester@mail.com");
        assertEquals(arr[1],"junittester");
    }

    @Test
    void decodePassword() {
        String[] arr = tokenService.decodePassword("anVuaXR0ZXN0ZXJAbWFpbC5jb206anVuaXR0ZXN0ZXI=").split(":");
        assertEquals(arr[1],"junittester");
    }

    @Test
    public void testTokenDecodePassWithNull(){
        assertNotNull(tokenService.decodePassword(null));
    }

    @Test
    public void testTokenDecodeTokenWithNotCorrectParams(){
        String[] arr = tokenService.decodePassword("brb4634634u34nfgbr567453434536734734535bGRI=").split(":");
        assertNotEquals(arr[0],"junittester@mail.com");
        assertNotEquals(arr[1],"junittester");
    }

    @Test
    public void testTokenDecodeTokenWithNullParam(){
        assertNotNull(tokenService.decodeToken(null));
    }
}
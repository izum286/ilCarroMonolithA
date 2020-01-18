package com.telran.ilcarro.ServiceTests;

import com.telran.ilcarro.config.TestsConfig;
import com.telran.ilcarro.service.auth.TokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestsConfig.class})
public class TokenServiceTests {

    //UserEmail: junittester@mail.com
    //Password: junittester
    //Token Base64: anVuaXR0ZXN0ZXJAbWFpbC5jb206anVuaXR0ZXN0ZXI=

    @Autowired
    TokenService tokenService;

    @Test
    public void testTokenDecodePass(){
        String[] arr = tokenService.decodePassword("anVuaXR0ZXN0ZXJAbWFpbC5jb206anVuaXR0ZXN0ZXI=").split(":");
        assertEquals(arr[1],"junittester");
    }

    @Test
    public void testTokenDecodePassWithNull(){
        assertNotNull(tokenService.decodePassword(null));
    }

    @Test
    public void testTokenDecodeToken(){
        String[] arr = tokenService.decodePassword("anVuaXR0ZXN0ZXJAbWFpbC5jb206anVuaXR0ZXN0ZXI=").split(":");
        assertEquals(arr[0],"junittester@mail.com");
        assertEquals(arr[1],"junittester");
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

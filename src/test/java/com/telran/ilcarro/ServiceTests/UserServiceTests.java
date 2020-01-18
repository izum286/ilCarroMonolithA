package com.telran.ilcarro.ServiceTests;

import com.telran.ilcarro.config.TestsConfig;
import com.telran.ilcarro.model.user.FullUserDTO;
import com.telran.ilcarro.model.user.RegUserDTO;
import com.telran.ilcarro.model.user.UpdUserDTO;
import com.telran.ilcarro.service.user.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestsConfig.class})
public class UserServiceTests {

    //UserEmail: junittester@mail.com
    //Password: junittester
    //Token Base64: anVuaXR0ZXN0ZXJAbWFpbC5jb206anVuaXR0ZXN0ZXI=

    //New user mail: vasyapupkin1234@mail.com
    //New user name: Vasya
    //New user lastName: Pupkin

    @Autowired
    UserService userService;

    RegUserDTO regUserDTO;
    UpdUserDTO updUserDTO;

    @Before
    public void setUp(){
        regUserDTO = RegUserDTO.builder()
                .firstName("Vasya")
                .lastName("Pupkin")
                .build();
        updUserDTO = UpdUserDTO.builder()
                .firstName("Sofa")
                .lastName("Beller")
                .photo("http://someurl.com")
                .build();
    }

    @After
    public void afterTest(){
    }

    @Test
    public void testUserAddNewUser(){
        assertTrue(userService.addUser("vasyapupkin1234@mail.com",regUserDTO).isPresent());
    }

    @Test
    public void testUserGetUser(){
        userService.addUser("vasyapupkin1234@mail.com",regUserDTO);
        if(userService.getUser("vasyapupkin1234@mail.com").isPresent()){
            assertEquals(userService.getUser("vasyapupkin1234@mail.com").get().getFirst_name(),regUserDTO.getFirstName());
        }
    }

    @Test
    public void testUserUpdateUser(){
        userService.addUser("vasyapupkin1234@mail.com",regUserDTO);
        FullUserDTO tmp = userService.getUser("vasyapupkin1234@mail.com").orElseThrow();
        FullUserDTO tmp2 = userService.updateUser("vasyapupkin1234@mail.com",updUserDTO).orElseThrow();
        assertNotEquals(tmp.getFirst_name(),tmp2.getFirst_name());
        assertNotEquals(tmp.getSecond_name(),tmp2.getSecond_name());
        assertNotEquals(tmp.getPhoto(),tmp2.getPhoto());
        assertEquals(tmp.getComments(),tmp2.getComments());
        assertEquals(tmp.getHistory(),tmp2.getHistory());
        assertEquals(tmp.getRegistration_date(),tmp2.getRegistration_date());
        assertEquals(tmp.getBooked_car(),tmp2.getBooked_car());
        assertEquals(tmp.getOwn_cars(),tmp2.getOwn_cars());
    }

    @Test
    public void testUserDeleteUser(){
        userService.addUser("vasyapupkin1234@mail.com",regUserDTO);
        assertTrue(userService.deleteUser("vasyapupkin1234@mail.com"));
        userService.addUser("vasyapupkin1234@mail.com",regUserDTO);
        assertTrue(userService.deleteUser("vasyapupkin1234@mail.com"));
        userService.addUser("vasyapupkin1234@mail.com",regUserDTO);
        assertTrue(userService.deleteUser("vasyapupkin1234@mail.com"));
    }


//    deleteUser
//    addUserCar
//    ifUserCarsExist
//    addBookedPeriodToUserHistory
}

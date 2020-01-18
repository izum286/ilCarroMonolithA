package com.telran.ilcarro.service.user;

import com.telran.ilcarro.model.user.FullUserDTO;
import com.telran.ilcarro.model.user.RegUserDTO;
import com.telran.ilcarro.model.user.UpdUserDTO;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {

    @MockBean
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

    @Test
    void addUser() {
        assertTrue(userService.addUser("vasyapupkin1234@mail.com",regUserDTO).isPresent());
    }

    @Test
    void getUser() {
        userService.addUser("vasyapupkin1234@mail.com",regUserDTO);
        if(userService.getUser("vasyapupkin1234@mail.com").isPresent()){
            assertEquals(userService.getUser("vasyapupkin1234@mail.com").get().getFirst_name(),regUserDTO.getFirstName());
        }
    }

    @Test
    void updateUser() {
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
    void deleteUser() {
        userService.addUser("vasyapupkin1234@mail.com",regUserDTO);
        assertTrue(userService.deleteUser("vasyapupkin1234@mail.com"));
        userService.addUser("vasyapupkin1234@mail.com",regUserDTO);
        assertTrue(userService.deleteUser("vasyapupkin1234@mail.com"));
        userService.addUser("vasyapupkin1234@mail.com",regUserDTO);
        assertTrue(userService.deleteUser("vasyapupkin1234@mail.com"));
    }

    @Test
    void addUserCar() {
    }

    @Test
    void ifUserCarsExist() {
    }

    @Test
    void getUserBookedCarsPeriods() {
    }

    @Test
    void addBookedPeriodToUserHistory() {
    }
}
package com.telran.ilcarro.service.user;

import com.telran.ilcarro.model.user.FullUserDTO;
import com.telran.ilcarro.model.user.RegUserDTO;
import com.telran.ilcarro.model.user.UpdUserDTO;
import com.telran.ilcarro.repository.UserEntityRepository;
import com.telran.ilcarro.repository.entity.LocationEntity;
import com.telran.ilcarro.repository.entity.UserEntity;
import com.telran.ilcarro.repository.exception.ConflictRepositoryException;
import com.telran.ilcarro.repository.exception.NotFoundRepositoryException;
import com.telran.ilcarro.service.exceptions.ConflictServiceException;
import com.telran.ilcarro.service.exceptions.NotFoundServiceException;
import com.telran.ilcarro.service.exceptions.ServiceException;
import com.telran.ilcarro.service.mapper.UserMapper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static java.util.Collections.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;
    @MockBean
    UserEntityRepository userRepository;

    private RegUserDTO regUserDTO;
    private UpdUserDTO updUserDTO;
    private FullUserDTO fullUserDTO;
    private FullUserDTO updatedFullUserDTO;
    private UserEntity userEntity;

    @Before
    public void init(){
        regUserDTO = RegUserDTO.builder()
                .firstName("Vasya")
                .lastName("Pupkin")
                .build();
        updUserDTO = UpdUserDTO.builder()
                .firstName("Sofa")
                .lastName("Beller")
                .photo("http://someurl.com")
                .build();
        fullUserDTO = FullUserDTO.builder()
                .photo("https://someurl.com/image.jpeg")
                .booked_car(emptyList())
                .comments(emptyList())
                .first_name("Vasya")
                .history(emptyList())
                .own_cars(emptyList())
                .registration_date(LocalDateTime.now().plusHours(23))
                .second_name("Pupkin")
                .build();
        updatedFullUserDTO = FullUserDTO.builder()
                .photo("http://someurl.com")
                .booked_car(emptyList())
                .comments(emptyList())
                .first_name("Sofa")
                .history(emptyList())
                .own_cars(emptyList())
                .registration_date(LocalDateTime.now().plusHours(23))
                .second_name("Beller")
                .build();
        userEntity = UserEntity.builder()
                .email("vasyapupkin1234@mail.com")
                .comments(emptyList())
                .firstName("Vasya")
                .history(emptyList())
                .lastName("Pupkin")
                .photo("https://someurl.com/image.jpeg")
                .registrationDate(fullUserDTO.getRegistration_date())
                .driverLicense("5r1325136135")
                .isDeleted(false)
                .location(LocationEntity.builder()
                        .zip(12124)
                        .street("strit")
                        .state("staaate")
                        .lon("4323.564345")
                        .lat("5345.323423")
                        .isVehicle(false)
                        .country("cauntri")
                        .city("ceeety")
                        .build())
                .ownCars(emptyList())
                .phone("452346236235")
                .build();
    }

    @Test
    void addUser() {
        //Annotation @Before not worked. Used method init for initialization of all entities and dtos, because we dont use DB
        init();
        doReturn(userEntity).when(userRepository).save(ArgumentMatchers.any());
        doReturn(Optional.of(userEntity)).when(userRepository).findById(ArgumentMatchers.anyString());
        Optional<FullUserDTO> check = userService.addUser("vasyapupkin1234@mail.com",regUserDTO);
        check.ifPresent(userDTO -> assertNotNull(userDTO.getRegistration_date()));
        verify(userRepository, times(1)).save(ArgumentMatchers.any(UserEntity.class));
        verify(userRepository, times(3)).findById("vasyapupkin1234@mail.com");
    }

    @Test
    void addUserWithNullEmail() {
        init();
        assertThrows(ServiceException.class,()->userService.addUser(null,regUserDTO));
        verify(userRepository, times(1)).save(ArgumentMatchers.any());
        verify(userRepository, times(0)).findById(ArgumentMatchers.any());
    }

    @Test
    void addUserWithNullRegUserDTO() {
        init();
        assertThrows(ServiceException.class,()->userService.addUser("vasyapupkin1234@mail.com",null));
        verify(userRepository, times(1)).save(ArgumentMatchers.any());
        verify(userRepository, times(0)).findById(ArgumentMatchers.any());
    }
    @Test
    void addUserWithNullAllArgs() {
        init();
        assertThrows(ServiceException.class,()->userService.addUser(null,null));
        verify(userRepository, times(1)).save(ArgumentMatchers.any());
        verify(userRepository, times(0)).findById(ArgumentMatchers.any());
    }

    @Test
    void addUserIfExists(){
        init();
        doThrow(ConflictRepositoryException.class).when(userRepository).save(ArgumentMatchers.any());
        assertThrows(ConflictServiceException.class,()->userService.addUser("vasyapupkin1234@mail.com",regUserDTO));
        verify(userRepository, times(1)).save(ArgumentMatchers.any());
    }

    @Test
    void getUser() {
        init();
        doReturn(Optional.of(userEntity)).when(userRepository).findById(ArgumentMatchers.anyString());
        Optional<FullUserDTO> check = userService.getUser("vasyapupkin1234@mail.com");
        check.ifPresent((dto)->assertEquals(dto.getRegistration_date(),userEntity.getRegistrationDate()));
        verify(userRepository, times(3)).findById("vasyapupkin1234@mail.com");
    }

    @Test
    void getUserWithNullEmail(){
        assertThrows(ServiceException.class,()->userService.getUser(null));
    }

    @Test
    void getUserIfNotExists(){
        doThrow(NotFoundRepositoryException.class).when(userRepository).findById(ArgumentMatchers.anyString());
        assertThrows(NotFoundServiceException.class,()->userService.getUser("djigurda@mail.com"));
    }

    @Test
    void updateUser() {
        init();

    }

    @Test
    void deleteUser() {
        init();

    }

    @Test
    void addUserCar() {
        init();
    }

    @Test
    void ifUserCarsExist() {
        init();
    }

    @Test
    void getUserBookedCarsPeriods() {
        init();
    }

    @Test
    void addBookedPeriodToUserHistory() {
        init();
    }
}
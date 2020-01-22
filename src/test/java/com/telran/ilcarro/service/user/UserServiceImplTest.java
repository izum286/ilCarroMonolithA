package com.telran.ilcarro.service.user;

import com.telran.ilcarro.model.user.FullUserDTO;
import com.telran.ilcarro.model.user.RegUserDTO;
import com.telran.ilcarro.model.user.UpdUserDTO;
import com.telran.ilcarro.repository.UserDetailsRepository;
import com.telran.ilcarro.repository.UserEntityRepository;
import com.telran.ilcarro.repository.entity.LocationEntity;
import com.telran.ilcarro.repository.entity.UserEntity;
import com.telran.ilcarro.repository.exception.ConflictRepositoryException;
import com.telran.ilcarro.repository.exception.NotFoundRepositoryException;
import com.telran.ilcarro.service.exceptions.ConflictServiceException;
import com.telran.ilcarro.service.exceptions.NotFoundServiceException;
import com.telran.ilcarro.service.exceptions.ServiceException;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;
    @MockBean
    UserEntityRepository userRepository;
    @MockBean
    UserDetailsRepository userDetailsRepository;

    private RegUserDTO regUserDTO;
    private UpdUserDTO updUserDTO;
    private FullUserDTO fullUserDTO;
    private FullUserDTO updatedFullUserDTO;
    private UserEntity userEntity;
    private UserEntity updatedUserEntity;
    private UserEntity userEntityDeleted;

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
        updatedUserEntity = UserEntity.builder()
                .email("vasyapupkin1234@mail.com")
                .comments(emptyList())
                .firstName("Sofa")
                .history(emptyList())
                .lastName("Beller")
                .photo("http://someurl.com")
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
        userEntityDeleted = UserEntity.builder()
                .email("vasyapupkin1234@mail.com")
                .comments(emptyList())
                .firstName("Vasya")
                .history(emptyList())
                .lastName("Pupkin")
                .photo("https://someurl.com/image.jpeg")
                .registrationDate(fullUserDTO.getRegistration_date())
                .driverLicense("5r1325136135")
                .isDeleted(true)
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
        doReturn(userEntity).when(userRepository).save(any());
        doReturn(Optional.of(userEntity)).when(userRepository).findById(anyString());
        Optional<FullUserDTO> check = userService.addUser("vasyapupkin1234@mail.com",regUserDTO);
        check.ifPresent(userDTO -> assertNotNull(userDTO.getRegistration_date()));
        verify(userRepository, times(1)).save(any(UserEntity.class));
        verify(userRepository, times(3)).findById("vasyapupkin1234@mail.com");
    }

    @Test
    void addUserWithNullEmail() {
        init();
        assertThrows(ServiceException.class,()->userService.addUser(null,regUserDTO));
        verify(userRepository, times(1)).save(any());
        verify(userRepository, times(0)).findById(any());
    }

    @Test
    void addUserWithNullRegUserDTO() {
        init();
        assertThrows(ServiceException.class,()->userService.addUser("vasyapupkin1234@mail.com",null));
        verify(userRepository, times(1)).save(any());
        verify(userRepository, times(0)).findById(any());
    }
    @Test
    void addUserWithNullAllArgs() {
        init();
        assertThrows(ServiceException.class,()->userService.addUser(null,null));
        verify(userRepository, times(1)).save(any());
        verify(userRepository, times(0)).findById(any());
    }

    @Test
    void addUserIfExists(){
        init();
        doThrow(ConflictRepositoryException.class).when(userRepository).save(any());
        assertThrows(ConflictServiceException.class,()->userService.addUser("vasyapupkin1234@mail.com",regUserDTO));
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void getUser() {
        init();
        doReturn(Optional.of(userEntity)).when(userRepository).findById(anyString());
        Optional<FullUserDTO> check = userService.getUser("vasyapupkin1234@mail.com");
        check.ifPresent((dto)->assertEquals(dto.getRegistration_date(),userEntity.getRegistrationDate()));
        verify(userRepository, times(3)).findById("vasyapupkin1234@mail.com");
    }

    @Test
    void getUserWithNullEmail(){
        assertThrows(ServiceException.class,()->userService.getUser(null));
        verify(userRepository,times(1)).findById(null);
    }

    @Test
    void getUserIfNotExists(){
        doThrow(NotFoundRepositoryException.class).when(userRepository).findById(anyString());
        assertThrows(NotFoundServiceException.class,()->userService.getUser("djigurda@mail.com"));
        verify(userRepository,times(1)).findById(anyString());
    }

    @Test
    void updateUser() {
        init();
        doReturn(true).when(userDetailsRepository).existsById(anyString());
        doReturn(Optional.of(userEntity)).when(userRepository).findById("vasyapupkin1234@mail.com");
        doReturn(updatedUserEntity).when(userRepository).save(any());
        Optional<FullUserDTO> updatedDto = userService.updateUser("vasyapupkin1234@mail.com",updUserDTO);
        updatedDto.ifPresent(dto -> {
            assertNotNull(dto.getRegistration_date());
            assertNotEquals(fullUserDTO.getFirst_name(),dto.getFirst_name());
            assertNotEquals(fullUserDTO.getSecond_name(),dto.getSecond_name());
            assertNotEquals(fullUserDTO.getPhoto(),dto.getPhoto());
        });
        verify(userRepository,times(4)).findById("vasyapupkin1234@mail.com");
        verify(userDetailsRepository,times(1)).existsById("vasyapupkin1234@mail.com");
    }

    @Test
    void updateUserIfNotExists(){
        init();
        doReturn(false).when(userDetailsRepository).existsById(anyString());
        //срабатывает эксэпшн нот фаунд сервис эксэпшн, но метод не тормозит на нём и идёт далее в сервис эксепшн.
        assertThrows(NotFoundServiceException.class,()->userService.updateUser("jigurda@mail.com",updUserDTO));
    }

    @Test
    void updateUserIfEmailNull(){
        init();
        assertThrows(ServiceException.class,()->userService.updateUser(null,updUserDTO));
        verify(userDetailsRepository,times(1)).existsById(any());
    }

    @Test
    void updateUserIfUpdUserDtoNull(){
        init();
        doReturn(Optional.of(userEntity)).when(userRepository).findById("vasyapupkin1234@mail.com");
        doReturn(true).when(userDetailsRepository).existsById("vasyapupkin1234@mail.com");
        assertDoesNotThrow(()->userService.updateUser("vasyapupkin1234@mail.com",null));
        verify(userDetailsRepository,times(1)).existsById(anyString());
        verify(userRepository,times(4)).findById(anyString());
    }

    @Test
    void deleteUser() {
        init();
        doReturn(Optional.of(userEntity)).when(userRepository).findById("vasyapupkin1234@mail.com");
        doReturn(userEntityDeleted).when(userRepository).save(any());
        assertTrue(()->userService.deleteUser("vasyapupkin1234@mail.com"));
        Optional<UserEntity> check = userRepository.findById("vasyapupkin1234@mail.com");
        check.ifPresent((e)->assertTrue(e.isDeleted()));
        verify(userRepository,times(2)).findById(anyString());
        verify(userRepository,times(1)).save(any());
    }

    @Test
    void deleteUserIfNoExists(){
        doThrow(NotFoundRepositoryException.class).when(userRepository).findById(anyString());
        assertThrows(NotFoundServiceException.class,()->userService.deleteUser("jigurda@mail.com"));
        verify(userRepository,times(1)).findById(anyString());
    }

    @Test
    void deleteUserIfEmailNull(){
        assertThrows(ServiceException.class,()->userService.deleteUser(null));
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
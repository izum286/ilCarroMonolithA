package com.telran.ilcarro.service.user;

import com.telran.ilcarro.model.car.BookedCarDto;
import com.telran.ilcarro.model.user.FullUserDTO;
import com.telran.ilcarro.model.user.RegUserDTO;
import com.telran.ilcarro.model.user.UpdUserDTO;
import com.telran.ilcarro.repository.UserDetailsRepository;
import com.telran.ilcarro.repository.UserEntityRepository;
import com.telran.ilcarro.repository.entity.BookedPeriodEntity;
import com.telran.ilcarro.repository.entity.LocationEntity;
import com.telran.ilcarro.repository.entity.PersonWhoBooked;
import com.telran.ilcarro.repository.entity.UserEntity;
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
import java.util.ArrayList;
import java.util.List;
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
    private UserEntity userEntityDeleted;
    private BookedPeriodEntity newBookedPeriodEntity;

    @Test
    void addUser() {
        init();
        doReturn(userEntity).when(userRepository).save(any());
        doReturn(Optional.of(userEntity)).when(userRepository).findById(anyString());
        Optional<FullUserDTO> check = assertDoesNotThrow(()->userService.addUser("vasyapupkin1234@mail.com", regUserDTO));
        check.ifPresent(userDTO -> assertNotNull(userDTO.getRegistration_date()));
    }

    @Test
    void addUserWithNullEmail() {
        init();
        assertThrows(ServiceException.class, () -> userService.addUser(null, regUserDTO));
    }

    @Test
    void addUserWithNullRegUserDTO() {
        init();
        assertThrows(ServiceException.class, () -> userService.addUser("vasyapupkin1234@mail.com", null));
    }

    @Test
    void addUserWithNullAllArgs() {
        init();
        assertThrows(ServiceException.class, () -> userService.addUser(null, null));
    }

    @Test
    void addUserIfExists() {
        init();
        doThrow(ConflictServiceException.class).when(userRepository).save(any());
        assertThrows(ConflictServiceException.class, () -> userService.addUser("vasyapupkin1234@mail.com", regUserDTO));
    }

    @Test
    void getUser() {
        init();
        doReturn(Optional.of(userEntity)).when(userRepository).findById(anyString());
        Optional<FullUserDTO> check = assertDoesNotThrow(()->userService.getUser("vasyapupkin1234@mail.com"));
        check.ifPresent((dto) -> assertEquals(dto.getRegistration_date(), userEntity.getRegistrationDate()));
    }

    @Test
    void getUserWithNullEmail() {
        assertThrows(ServiceException.class, () -> userService.getUser(null));
    }

    @Test
    void getUserIfNotExists() {
        doThrow(ServiceException.class).when(userRepository).findById(anyString());
        assertThrows(ServiceException.class, () -> userService.getUser("djigurda@mail.com"));
    }

    @Test
    void updateUser() {
        init();
        doReturn(true).when(userDetailsRepository).existsById(anyString());
        doReturn(Optional.of(userEntity)).when(userRepository).findById("vasyapupkin1234@mail.com");
        doReturn(userEntity).when(userRepository).save(any());
        Optional<FullUserDTO> updatedDto = assertDoesNotThrow(()->userService.updateUser("vasyapupkin1234@mail.com", updUserDTO));
        updatedDto.ifPresent(dto -> {
            assertNotNull(dto.getRegistration_date());
            assertNotEquals(fullUserDTO.getFirst_name(), dto.getFirst_name());
            assertNotEquals(fullUserDTO.getSecond_name(), dto.getSecond_name());
            assertNotEquals(fullUserDTO.getPhoto(), dto.getPhoto());
        });
    }

    @Test
    void updateUserIfNotExists() {
        init();
        doReturn(false).when(userDetailsRepository).existsById(anyString());
        assertThrows(ServiceException.class, () -> userService.updateUser("jigurda@mail.com", updUserDTO));
    }

    @Test
    void updateUserIfNameAndSecondNameIsEmptyString(){
        init();
        doReturn(Optional.of(userEntity)).when(userRepository).findById("vasyapupkin1234@mail.com");
        doReturn(true).when(userDetailsRepository).existsById("vasyapupkin1234@mail.com");
        updUserDTO.setFirstName("");
        updUserDTO.setLastName("");
        assertThrows(ServiceException.class,()->userService.updateUser("vasyapupkin1234@mail.com",updUserDTO));
    }

    @Test
    void updateUserIfEmailNull() {
        init();
        assertThrows(ServiceException.class, () -> userService.updateUser(null, updUserDTO));
    }

    @Test
    void updateUserIfUpdUserDtoNull() {
        init();
        doReturn(Optional.of(userEntity)).when(userRepository).findById("vasyapupkin1234@mail.com");
        doReturn(true).when(userDetailsRepository).existsById("vasyapupkin1234@mail.com");
        assertThrows(ServiceException.class,() -> userService.updateUser("vasyapupkin1234@mail.com", null));
    }

    @Test
    void deleteUser() {
        init();
        doReturn(Optional.of(userEntity)).when(userRepository).findById("vasyapupkin1234@mail.com");
        doReturn(userEntityDeleted).when(userRepository).save(any());
        assertTrue(() -> userService.deleteUser("vasyapupkin1234@mail.com"));
        Optional<UserEntity> check = assertDoesNotThrow(()->userRepository.findById("vasyapupkin1234@mail.com"));
        check.ifPresent((e) -> assertTrue(e.isDeleted()));
    }

    @Test
    void deleteUserIfNotExists() {
        doThrow(NotFoundServiceException.class).when(userRepository).findById(anyString());
        assertThrows(NotFoundServiceException.class, () -> userService.deleteUser("jigurda@mail.com"));
    }

    @Test
    void deleteUserIfEmailNull() {
        assertThrows(ServiceException.class, () -> userService.deleteUser(null));
    }

    @Test
    void addUserCar() {
        init();
        doReturn(Optional.of(userEntity)).when(userRepository).findById(anyString());
        assertTrue(userService.addUserCar("vasyapupkin1234@mail.com", "23-333-54"));
        Optional<FullUserDTO> check = assertDoesNotThrow(()->userService.getUser("vasyapupkin1234@mail.com"));
    }

    @Test
    void addUserCarIfUserCarExists() {
        init();
        doReturn(Optional.of(userEntity)).when(userRepository).findById("vasyapupkin1234@mail.com");
        assertThrows(ConflictServiceException.class, () -> userService.addUserCar("vasyapupkin1234@mail.com", "32-222-23"));
    }

    @Test
    void addUserCarIfCarSerialNull() {
        assertThrows(ServiceException.class, () -> userService.addUserCar("vasyapupkin1234@mail.com", null));
    }

    @Test
    void addUserCarIfUserNotExists() {
        doThrow(NotFoundServiceException.class).when(userRepository).findById("jigurda@mail.com");
        assertThrows(NotFoundServiceException.class, () -> userService.addUserCar("jigurda@mail.com", "ja-jaj-ja"));
    }

    @Test
    void ifUserCarsExistMethodOnlyTest() {
        init();
        doReturn(Optional.of(userEntity)).when(userRepository).findById("vasyapupkin1234@mail.com");
        assertThrows(ConflictServiceException.class, () -> userService.addUserCar("vasyapupkin1234@mail.com", "32-222-23"));
    }

    @Test
    void getUserBookedCarsPeriods() {
        init();
        doReturn(Optional.of(userEntity)).when(userRepository).findById("vasyapupkin1234@mail.com");
        Optional<List<BookedCarDto>> check = assertDoesNotThrow(()->userService.getUserBookedCarsPeriods("vasyapupkin1234@mail.com"));
        check.ifPresent((e) -> assertEquals("32-222-23", e.get(0).getSerial_number()));
    }

    @Test
    void getUserBookedCarsPeriodsIfUserNotExists() {
        init();
        doThrow(NotFoundServiceException.class).when(userRepository).findById("jigurda@mail.com");
        assertThrows(NotFoundServiceException.class, () -> userService.getUserBookedCarsPeriods("jigurda@mail.com"));
    }

    @Test
    void getUserBookedCarsPeriodIfEmailNull() {
        assertThrows(ServiceException.class, () -> userService.getUserBookedCarsPeriods(null));
    }

    @Test
    void addBookedPeriodToUserHistory() {
        init();
        doReturn(Optional.of(userEntity)).when(userRepository).findById("vasyapupkin1234@mail.com");
        assertDoesNotThrow(()->assertTrue(userService.addBookedPeriodToUserHistory("vasyapupkin1234@mail.com", newBookedPeriodEntity)));
        List<BookedPeriodEntity> check = userEntity.getHistory();
        if (check != null){
            assertEquals("23-222-23", check.get(0).getCarId());
            assertEquals("23-333-23", check.get(1).getCarId());
        }
    }

    @Test
    void addBookedPeriodToUserHistoryIfEmailNull(){
       assertThrows(ServiceException.class,()->userService.addBookedPeriodToUserHistory(null,newBookedPeriodEntity));
    }

    @Test
    void addBookedPeriodToUserHistoryIfSecondArgNull(){
        assertThrows(ServiceException.class,()->userService.addBookedPeriodToUserHistory("vasyapupkin1234@mail.com",null));
    }

    @Test
    void addBookedPeriodToUserHistoryIfAllArgsNull(){
        assertThrows(ServiceException.class,()->userService.addBookedPeriodToUserHistory(null,null));
    }

    @Before
    //Annotation @Before not worked. Used method init for initialization of all entities and dtos, because we dont use DB
    public void init() {
        List<String> carSerialLiesList = new ArrayList<>();
        carSerialLiesList.add("32-222-23");
        List<BookedPeriodEntity> bookedPeriodLieList = new ArrayList<>();
        bookedPeriodLieList.add(BookedPeriodEntity.builder()
                .paid(true)
                .active(true)
                .amount(23423)
                .bookingDate(LocalDateTime.now().minusDays(3))
                .endDateTime(LocalDateTime.now().plusHours(23))
                .orderId("12345")
                .personWhoBooked(PersonWhoBooked.builder()
                        .email("vasyapupkin1234@mail.com")
                        .first_name("Vasya")
                        .phone("1234567899")
                        .second_name("Pupkin")
                        .build())
                .startDateTime(LocalDateTime.now().minusDays(2))
                .carId("32-222-23")
                .build());
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
                .history(null)
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
                .ownCars(carSerialLiesList)
                .phone("452346236235")
                .bookedCars(bookedPeriodLieList)
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
        newBookedPeriodEntity = BookedPeriodEntity.builder()
                .carId("32-333-23")
                .startDateTime(LocalDateTime.now())
                .personWhoBooked(PersonWhoBooked.builder()
                        .email("vasyapupkin1234@mail.com")
                        .first_name("Vasya")
                        .phone("1234567899")
                        .second_name("Pupkin")
                        .build())
                .orderId("12346")
                .endDateTime(LocalDateTime.now().plusHours(23))
                .bookingDate(LocalDateTime.now().minusDays(1))
                .amount(1234)
                .active(true)
                .paid(false)
                .build();
    }
}

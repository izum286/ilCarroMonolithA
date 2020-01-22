package com.telran.ilcarro.service.car;

import com.telran.ilcarro.repository.BookedPeriodsRepository;
import com.telran.ilcarro.repository.CarRepository;
import com.telran.ilcarro.repository.UserEntityRepository;
import com.telran.ilcarro.repository.entity.UserEntity;
import com.telran.ilcarro.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CarServiceImplTest {

    @Autowired
    CarService carService;

    @Autowired
    UserService userService;

    @MockBean
    CarRepository carRepository;

    @MockBean
    UserEntityRepository userRepository;

    @MockBean
    BookedPeriodsRepository bookedPeriodsRepository;

    @Test
    void addCar() {
    }

    @Test
    void updateCar() {
    }

    @Test
    void deleteCar() {
    }

    @Test
    void getCarByIdForUsers() {
    }

    @Test
    void getCarByIdForOwner() {
    }

    @Test
    void ownerGetCars() {
    }

    @Test
    void getBookedPeriodsByCarId() {
    }

    @Test
    void makeReservation() {
    }

    @Test
    void getThreeBestCars() {
    }

    @Test
    void getCarStatById() {
    }
}
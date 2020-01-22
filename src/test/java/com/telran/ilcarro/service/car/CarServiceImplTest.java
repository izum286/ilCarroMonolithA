package com.telran.ilcarro.service.car;

import com.telran.ilcarro.model.car.BookedPeriodDto;
import com.telran.ilcarro.model.car.FeatureDto;
import com.telran.ilcarro.model.car.FullCarDTOResponse;
import com.telran.ilcarro.model.car.PickUpPlaceDto;
import com.telran.ilcarro.model.user.OwnerDtoResponse;
import com.telran.ilcarro.model.user.PersonWhoBookedDto;
import com.telran.ilcarro.repository.BookedPeriodsRepository;
import com.telran.ilcarro.repository.CarRepository;
import com.telran.ilcarro.repository.UserEntityRepository;
import com.telran.ilcarro.repository.entity.*;
import com.telran.ilcarro.service.mapper.BookedPeriodMapper;
import com.telran.ilcarro.service.mapper.FeatureMapper;
import com.telran.ilcarro.service.mapper.PickUpPlaceMapper;
import com.telran.ilcarro.service.user.UserService;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
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

    private UserEntity userEntity;
    private FullCarDTOResponse fullCarDTOResponse;
    private FullCarEntity fullCarEntity;
    private OwnerEntity ownerEntity;


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

    @Before
    void init(){
        List<String> carSerialLiesList = new ArrayList<>();
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
        userEntity = UserEntity.builder()
                .email("vasyapupkin1234@mail.com")
                .comments(emptyList())
                .firstName("Vasya")
                .history(null)
                .lastName("Pupkin")
                .photo("https://someurl.com/image.jpeg")
                .registrationDate(LocalDateTime.now().minusDays(24))
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

        List<BookedPeriodDto> bookedPeriodDtos = new ArrayList<>();
        bookedPeriodDtos.add(BookedPeriodDto.builder()
                        .paid(true)
                        .amount(31244f)
                        .booking_date(LocalDateTime.now().minusDays(2).toString())
                        .end_date_time(LocalDateTime.now().plusHours(23))
                        .order_id("12345")
                        .person_who_booked(PersonWhoBookedDto.builder()
                                .email("vasyapupkin1234@mail.com")
                                .first_name("Vasya")
                                .phone("1234567899")
                                .second_name("Pupkin")
                                .build())
                        .start_date_time(LocalDateTime.now().minusDays(2))
                        .build());

        fullCarDTOResponse = FullCarDTOResponse.builder()
                .serialNumber("32-222-23")
                .bookedPeriodDto(bookedPeriodDtos)
                .torque(84)
                .horsePower(96)
                .year("2010")
                .wheelsDrive("fwd")
                .seats(5)
                .pickUpPlace(PickUpPlaceDto.builder()
                        .place_id("35235235135134")
                        .longitude(42312.4443412344f)
                        .latitude(34512.5136246236f)
                        .build())
                .model("3")
                .make("Mazda")
                .imageUrl(List.of("https://someurlwithsomeimage.com/image.jpg"))
                .gear("manual")
                .fuelConsumption(10.0f)
                .fuel("kerosine")
                .features(List.of(
                        FeatureDto.builder().feature("MAGNITOLA!!!").build(),
                        FeatureDto.builder().feature("JIGURDA!").build()))
                .engine("2L")
                .doors(5)
                .distanceIncluded(100)
                .carClass("Active")
                .about("More more text about this car. Why not lorem ipsum? I dont know.")
                .owner(OwnerDtoResponse.builder()
                        .registrationDate(userEntity.getRegistrationDate().toString())
                        .lastName(userEntity.getLastName())
                        .firstName(userEntity.getFirstName())
                        .build())
                .statistics(null)
                .pricePerDay(100)
                .build();

        List<FeatureEntity> featureEntityList = fullCarDTOResponse.getFeatures()
                .stream()
                .map(FeatureMapper.INSTANCE::map)
                .collect(Collectors.toList());

        List<BookedPeriodEntity> bookedPeriodEntities = fullCarDTOResponse.getBookedPeriodDto()
                .stream()
                .map(BookedPeriodMapper.INSTANCE::map)
                .collect(Collectors.toList());

        fullCarEntity = FullCarEntity.builder()
                .year("2010")
                .wheelsDrive("FWD")
                .trips(1)
                .torque(87)
                .serialNumber("32-222-23")
                .seats(5)
                .model("3")
                .horsePower(96)
                .gear("manual")
                .fuelConsumption(104.4f)
                .fuel("kerosine")
                .engine("2L")
                .doors(5)
                .distanceIncluded(100)
                .carClass("Active")
                .about(fullCarDTOResponse.getAbout())
                .features(featureEntityList)
                .bookedPeriods(bookedPeriodEntities)
                .imageUrl(fullCarDTOResponse.getImageUrl())
                .make("Mazda")
                .pricePerDay(PricePerDayEntity.builder().currency("usd").value(100).build())
                .statistics(CarStatEntity.builder().rating(45.233f).trips(1).build())
                .pickUpPlace(PickUpPlaceMapper.INSTANCE.map(fullCarDTOResponse.getPickUpPlace()))
                .isDeleted(false)
                .owner(ownerEntity)
                .pricePerDaySimple("100$")
                .build();
        ownerEntity = OwnerEntity.builder()
                .registrationDate(userEntity.getRegistrationDate().toString())
                .lastName(userEntity.getLastName())
                .firstName(userEntity.getFirstName())
                .email(userEntity.getEmail())
                .build();
    }
}
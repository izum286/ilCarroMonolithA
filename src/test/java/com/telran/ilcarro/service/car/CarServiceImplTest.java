package com.telran.ilcarro.service.car;

import com.telran.ilcarro.model.car.*;
import com.telran.ilcarro.model.user.OwnerDtoResponse;
import com.telran.ilcarro.model.user.PersonWhoBookedDto;
import com.telran.ilcarro.repository.BookedPeriodsRepository;
import com.telran.ilcarro.repository.CarRepository;
import com.telran.ilcarro.repository.UserEntityRepository;
import com.telran.ilcarro.repository.entity.*;
import com.telran.ilcarro.service.exceptions.ConflictServiceException;
import com.telran.ilcarro.service.exceptions.NotFoundServiceException;
import com.telran.ilcarro.service.exceptions.ServiceException;
import com.telran.ilcarro.service.mapper.BookedPeriodMapper;
import com.telran.ilcarro.service.mapper.FeatureMapper;
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
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

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
    private AddUpdateCarDtoRequest addUpdateCarDtoRequest;


    @Test
    void addCar() {
        init();
        doReturn(Optional.of(userEntity)).when(userRepository).findById(anyString());
        doReturn(fullCarEntity).when(carRepository).save(any());
        carService.addCar(addUpdateCarDtoRequest,"vasyapupkin1234@mail.com");
        verify(userRepository,times(3)).findById(anyString());
        verify(carRepository,times(1)).save(any());
        verify(userRepository,times(1)).save(any());
    }

    @Test
    void addCarIfUserNotExists(){
        init();
        doThrow(NotFoundServiceException.class).when(userRepository).findById(anyString());
        assertThrows(NotFoundServiceException.class,()->carService.addCar(addUpdateCarDtoRequest,"jigurda@mail.com"));
    }

    @Test
    void addCarIfCarArgIsNull(){
        assertThrows(ServiceException.class,()->carService.addCar(null,"vasyapupkin1234@gmail.com"));
    }

    @Test
    void addCarIfAllArgsNull(){
        assertThrows(ServiceException.class,()->carService.addCar(null,null));
    }

    @Test
    void addCarIfSerialNumberOfCarIsNull(){
        init();
        addUpdateCarDtoRequest.setSerialNumber(null);
        assertThrows(ServiceException.class,()->carService.addCar(addUpdateCarDtoRequest,"vasyapupkin1234@mail.com"));
    }

    @Test
    void addCarIfCarWithSerialAlreadyExists(){
        init();
        AddUpdateCarDtoRequest tmp = addUpdateCarDtoRequest;
        UserEntity userTmp = userEntity;
        userEntity.setEmail("jigurda@mail.com");
        userEntity.setOwnCars(List.of(addUpdateCarDtoRequest.getSerialNumber()));
        doReturn(Optional.of(userTmp)).when(userRepository).findById(anyString());
        doReturn(userTmp).when(userRepository).save(any());
        doReturn(Optional.of(fullCarEntity)).when(carRepository).findById(anyString());
        doReturn(fullCarEntity).when(carRepository).save(any());
        assertThrows(ConflictServiceException.class,()->carService.addCar(tmp,"jigurda@mail.com"));
    }

    @Test
    void addCarIfCarHaveOnlyNullFieldsWithSerialNumber(){
        init();
        UserEntity userTmp = userEntity;
        userEntity.setEmail("jigurda@mail.com");
        AddUpdateCarDtoRequest tmp = new AddUpdateCarDtoRequest();
        tmp.setSerialNumber("11-111-11");
        doReturn(Optional.of(userTmp)).when(userRepository).findById(anyString());
        assertThrows(ServiceException.class,()->carService.addCar(tmp,"jigurda@mail.com"));
    }

    @Test
    void updateCar() {
        init();
        userEntity.setOwnCars(List.of(fullCarEntity.getSerialNumber()));
        doReturn(Optional.of(fullCarEntity)).when(carRepository).findById(anyString());
        doReturn(Optional.of(userEntity)).when(userRepository).findById(anyString());
        AddUpdateCarDtoRequest toTest = addUpdateCarDtoRequest;
        toTest.setEngine("1.6V");
        toTest.setDoors(8);
        toTest.setHorsePower(10000);
        Optional<FullCarDTOResponse> check = carService.updateCar(toTest,"vasyapupkin1234@mail.com");
        check.ifPresent(carDTOResponse -> {
            assertNotEquals("2L", carDTOResponse.getEngine());
            assertNotEquals("5",carDTOResponse.getDoors());
            assertNotEquals("96",carDTOResponse.getHorsePower());
            assertEquals(fullCarEntity.getEngine(),carDTOResponse.getEngine());
            assertEquals(fullCarEntity.getHorsePower(),carDTOResponse.getHorsePower());
            assertEquals(fullCarEntity.getDoors(),carDTOResponse.getDoors());
        });
    }

    @Test
    void updateCarIfUserHidingHaveCurrCar(){
        init();
        doReturn(Optional.of(userEntity)).when(userRepository).findById(anyString());
        doThrow(NotFoundServiceException.class).when(carRepository).findById(anyString());
        assertThrows(NotFoundServiceException.class,()->carService.updateCar(addUpdateCarDtoRequest,"vasyapupkin1234@mail.com"));
    }

    @Test
    void updateCarIfUserNotExists(){
        init();
        doThrow(NotFoundServiceException.class).when(userRepository).findById(anyString());
        assertThrows(NotFoundServiceException.class,()->carService.updateCar(addUpdateCarDtoRequest,"jigurda@mail.com"));
    }

    @Test
    void updateCarWithNullArgEmail(){
        init();
        assertThrows(ServiceException.class,()->carService.updateCar(addUpdateCarDtoRequest,null));
    }

    @Test
    void updateCarWithNullArgCarToUpd(){
        init();
        userEntity.setOwnCars(List.of("11-111-11"));
        doReturn(Optional.of(userEntity)).when(userRepository).findById("vasyapupkin1234@mail.com");
        assertThrows(ServiceException.class,()->carService.updateCar(null,"vasyapupkin1234@mail.com"));
    }

    @Test
    void updateCarWithAllArgsNull(){
        assertThrows(ServiceException.class,()->carService.updateCar(null,null));
    }

    @Test
    void updateCarWithCarSerialIsNull(){
        init();
        userEntity.setOwnCars(List.of("11-111-11"));
        doReturn(Optional.of(userEntity)).when(userRepository).findById("vasyapupkin1234@mail.com");
        doReturn(Optional.of(fullCarEntity)).when(carRepository).findById(anyString());
        addUpdateCarDtoRequest.setSerialNumber(null);
        assertThrows(ServiceException.class,()->carService.updateCar(addUpdateCarDtoRequest,"vasyapupkin1234@mail.com"));
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
    public void init() {
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

        List<String> imageList = new ArrayList<>();
        imageList.add("https://someurlwithsomeimage.com/image.jpg");

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
                .imageUrl(imageList)
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

        addUpdateCarDtoRequest = AddUpdateCarDtoRequest.builder()
                .about(fullCarDTOResponse.getAbout())
                .carClass(fullCarDTOResponse.getCarClass())
                .distanceIncluded(fullCarDTOResponse.getDistanceIncluded())
                .doors(fullCarDTOResponse.getDoors())
                .engine(fullCarDTOResponse.getEngine())
                .features(fullCarDTOResponse.getFeatures())
                .fuel(fullCarDTOResponse.getFuel())
                .fuelConsumption(fullCarDTOResponse.getFuelConsumption())
                .gear(fullCarDTOResponse.getGear())
                .horsePower(fullCarDTOResponse.getHorsePower())
                .imageUrl(fullCarDTOResponse.getImageUrl())
                .make(fullCarDTOResponse.getMake())
                .model(fullCarDTOResponse.getModel())
                .pickUpPlaceDto(fullCarDTOResponse.getPickUpPlace())
                .pricePerDay(fullCarDTOResponse.getDistanceIncluded())
                .seats(fullCarDTOResponse.getSeats())
                .serialNumber(fullCarDTOResponse.getSerialNumber())
                .torque(fullCarDTOResponse.getTorque())
                .wheelsDrive(fullCarDTOResponse.getWheelsDrive())
                .year(fullCarDTOResponse.getYear())
                .build();

        List<FeatureEntity> featureEntityList = fullCarDTOResponse.getFeatures()
                .stream()
                .map(FeatureMapper.INSTANCE::map)
                .collect(Collectors.toList());

        List<BookedPeriodEntity> bookedPeriodEntities = fullCarDTOResponse.getBookedPeriodDto()
                .stream()
                .map(BookedPeriodMapper.INSTANCE::map)
                .collect(Collectors.toList());

        CarStatEntity carStatEntity = CarStatEntity.builder().rating(45.233f).trips(1).build();

        PickUpPlaceEntity pickUpPlaceEntity = PickUpPlaceEntity.builder()
                .latitude(fullCarDTOResponse.getPickUpPlace().getLatitude())
                .longitude(fullCarDTOResponse.getPickUpPlace().getLongitude())
                .place_id(fullCarDTOResponse.getPickUpPlace().getPlace_id())
                .build();

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
                .statistics(carStatEntity)
                .pickUpPlace(pickUpPlaceEntity)
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
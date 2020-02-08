package com.telran.ilcarro.service.filter;

import com.telran.ilcarro.model.car.*;
import com.telran.ilcarro.model.filter.FilterDTO;
import com.telran.ilcarro.model.user.OwnerDtoResponse;
import com.telran.ilcarro.model.user.PersonWhoBookedDto;
import com.telran.ilcarro.repository.FilterRepository;
import com.telran.ilcarro.repository.entity.*;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.Collections.indexOfSubList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class FilterServiceImplTest {

    @Autowired
    FilterService filterService;

    @MockBean
    FilterRepository filterRepository;

    private AddUpdateCarDtoRequest addUpdateCarDtoRequest;
    private AddUpdateCarDtoRequest addUpdateCarDtoRequestToMerge;
    private FilterDTO filterDTO;
    private FilterDTO filterDTO2;
    private FilterNodeEntity rootNode;
    private FilterNodeEntity filterNodeEntity;
    private FilterNodeEntity filterNodeEntityToMerge;


    @Test
    void addFilter() {
        init();
        doReturn(Optional.of(rootNode)).when(filterRepository).findById("root");
        assertDoesNotThrow(()->filterService.addFilter(addUpdateCarDtoRequest));

    }

    @Test
    void addFilterIfDtoIsNull() {
        init();
        doReturn(Optional.of(rootNode)).when(filterRepository).findById("root");
        assertThrows(Throwable.class,()->filterService.addFilter(null));
    }

    //Checked with System.out.println
    @Test
    void provideFilter() {
        init();
        doReturn(Optional.of(rootNode)).when(filterRepository).findById("root");
        filterService.addFilter(addUpdateCarDtoRequest);
        assertDoesNotThrow(()-> filterService.provideFilter());
//        assertDoesNotThrow(()-> System.out.println(filterService.provideFilter()));
    }

//    @Test
//    void addNode() {
//        //cheked with method addFilter
//    }

    //Checked with System.out.println
    @Test
    void mergeNodes() {
        init();
        assertDoesNotThrow(()->filterService.mergeNodes(filterNodeEntity,filterNodeEntityToMerge));
        assertEquals("3",filterNodeEntity.getChilds().get(0).getValue());
        assertEquals("6",filterNodeEntity.getChilds().get(1).getChilds().get(0).getValue());
    }

    @Test
    void findNextIndx() {
        init();
        int i = assertDoesNotThrow(()->filterService.findNextIndx(filterNodeEntity,filterNodeEntityToMerge));
        assertEquals(1,i);
    }

    @Test
    void findNextIndxIfFirstArgIsNull() {
        init();
        assertThrows(RuntimeException.class,()->filterService.findNextIndx(null,filterNodeEntityToMerge));
    }

    @Test
    void findNextIndxIfSecondArgIsNull() {
        init();
        assertThrows(RuntimeException.class,()->filterService.findNextIndx(filterNodeEntity,null));
    }

    @Test
    void findNextIndxIfAllArgsNull() {
        init();
        assertThrows(RuntimeException.class,()->filterService.findNextIndx(null,null));
    }

    @Before
    public void init(){

        //added only for ctrl c - ctrl v, because is very lazy to write a new dto....... copied from carServiceImplTest
        //data is cheked
        //**************************************************************************
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

        UserEntity userEntity = UserEntity.builder()
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


        FullCarDTOResponse fullCarDTOResponse = FullCarDTOResponse.builder()
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
                        .registrationDate(userEntity.getRegistrationDate())
                        .lastName(userEntity.getLastName())
                        .firstName(userEntity.getFirstName())
                        .build())
                .statistics(null)
                .pricePerDay(100)
                .build();
        //**************************************************************************
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

        addUpdateCarDtoRequestToMerge = AddUpdateCarDtoRequest.builder()
                .about(fullCarDTOResponse.getAbout())
                .carClass(fullCarDTOResponse.getCarClass())
                .distanceIncluded(fullCarDTOResponse.getDistanceIncluded())
                .doors(2)
                .engine("1.6L")
                .features(fullCarDTOResponse.getFeatures())
                .fuel("diesel")
                .fuelConsumption(fullCarDTOResponse.getFuelConsumption())
                .gear("automat")
                .horsePower(fullCarDTOResponse.getHorsePower())
                .imageUrl(fullCarDTOResponse.getImageUrl())
                .make(fullCarDTOResponse.getMake())
                .model("6")
                .pickUpPlaceDto(fullCarDTOResponse.getPickUpPlace())
                .pricePerDay(fullCarDTOResponse.getDistanceIncluded())
                .seats(fullCarDTOResponse.getSeats())
                .serialNumber(fullCarDTOResponse.getSerialNumber())
                .torque(fullCarDTOResponse.getTorque())
                .wheelsDrive(fullCarDTOResponse.getWheelsDrive())
                .year(fullCarDTOResponse.getYear())
                .build();

        filterDTO = FilterDTO.builder()
                .engine("2l")
                .fuel("kerosine")
                .fuel_consumption("1L/100Km")
                .gear("manual")
                .horsepower("96")
                .make("mazda")
                .model("3")
                .wheels_drive("FWD")
                .year("2010")
                .build();

        filterDTO2 = FilterDTO.builder()
                .engine("2l")
                .fuel("diesel")
                .fuel_consumption("1L/100Km")
                .gear("manual")
                .horsepower("96")
                .make("mazda")
                .model("3")
                .wheels_drive("FWD")
                .year("2010")
                .build();

        FilterServiceImpl forOneMethod = new FilterServiceImpl();

        try {
            filterNodeEntity = forOneMethod.map(forOneMethod.map(addUpdateCarDtoRequest));
            filterNodeEntityToMerge = forOneMethod.map(forOneMethod.map(addUpdateCarDtoRequestToMerge));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        rootNode = new FilterNodeEntity("root", "root");
        try {
            rootNode.getChilds().add(forOneMethod.map(filterDTO));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}